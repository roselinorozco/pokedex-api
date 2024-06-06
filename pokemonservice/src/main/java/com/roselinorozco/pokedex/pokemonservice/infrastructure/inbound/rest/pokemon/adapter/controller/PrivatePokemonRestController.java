package com.roselinorozco.pokedex.pokemonservice.infrastructure.inbound.rest.pokemon.adapter.controller;

import com.roselinorozco.pokedex.pokemonservice.application.dto.*;
import com.roselinorozco.pokedex.pokemonservice.domain.exception.DeleteNotAllowedException;
import com.roselinorozco.pokedex.pokemonservice.domain.exception.UpdateNotAllowedException;
import com.roselinorozco.pokedex.pokemonservice.domain.model.Pokemon;
import com.roselinorozco.pokedex.pokemonservice.domain.model.PokemonPage;
import com.roselinorozco.pokedex.pokemonservice.domain.port.in.DeletePokemonInputPort;
import com.roselinorozco.pokedex.pokemonservice.domain.port.in.RegisterPokemonInputPort;
import com.roselinorozco.pokedex.pokemonservice.domain.port.in.GetPokemonsByOwnerInputPort;
import com.roselinorozco.pokedex.pokemonservice.domain.port.in.UpdatePokemonInputPort;
import com.roselinorozco.pokedex.pokemonservice.infrastructure.mapper.PokemonMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author Roselin Orozco
 */
@RestController
@RequestMapping("/api/private/pokemon")
public class PrivatePokemonRestController {

    private final RegisterPokemonInputPort registerPokemonInputPort;
    private final GetPokemonsByOwnerInputPort getPokemonsByOwnerInputPort;
    private final UpdatePokemonInputPort updatePokemonInputPort;
    private final DeletePokemonInputPort deletePokemonInputPort;
    private final PokemonMapper pokemonMapper;

    public PrivatePokemonRestController(final RegisterPokemonInputPort registerPokemonInputPort,
                                        final GetPokemonsByOwnerInputPort getPokemonsByOwnerInputPort,
                                        final UpdatePokemonInputPort updatePokemonInputPort,
                                        final DeletePokemonInputPort deletePokemonInputPort,
                                        final PokemonMapper pokemonMapper) {
        this.registerPokemonInputPort = registerPokemonInputPort;
        this.getPokemonsByOwnerInputPort = getPokemonsByOwnerInputPort;
        this.updatePokemonInputPort = updatePokemonInputPort;
        this.deletePokemonInputPort = deletePokemonInputPort;
        this.pokemonMapper = pokemonMapper;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedPokemonResponse> createPokemon(@RequestBody PokemonCreateRequest pokemonCreateRequest) {

        final Pokemon pokemonToCreate = this.pokemonMapper.convertToPokemon(pokemonCreateRequest);
        pokemonToCreate.setOwner(this.getAuthenticatedEmail());

        final Pokemon createdPokemon = this.registerPokemonInputPort.execute(pokemonToCreate);

        final CreatedPokemonResponse pokemonResponse = this.pokemonMapper.convertToCreatedPokemonResponse(createdPokemon);

        return new ResponseEntity<>(pokemonResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getPokemonsByOwnerID(@RequestParam(required = false) Integer page,
                                                  @RequestParam(required = false) Integer size) {

        final PokemonPage pokemonPage = this.getPokemonsByOwnerInputPort.execute(
                this.getAuthenticatedEmail(), page, size
        );

        final List<PokemonRecordResponse> pokemonRecordResponse = pokemonPage.pokemons().stream()
                .map(this.pokemonMapper::convertToPokemonRecordResponse).toList();

        final Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(new PageImpl<>(pokemonRecordResponse, pageable, pokemonPage.totalElements()));
    }

    @PatchMapping(value = "/{pokemonId}")
    public ResponseEntity<?> updatePokemon(@PathVariable String pokemonId,
                                           @RequestBody PokemonUpdateRequest pokemonUpdateRequest) {

        final Optional<Pokemon> updatedPokemon = this.updatePokemonInputPort.execute(
                this.pokemonMapper.convertToPokemon(pokemonUpdateRequest, pokemonId, this.getAuthenticatedEmail()));

        if(updatedPokemon.isPresent()) {
            UpdatedPokemonResponse updatedPokemonResponse = new UpdatedPokemonResponse(
                    pokemonId,
                    updatedPokemon.get().getName(),
                    updatedPokemon.get().getType(),
                    updatedPokemon.get().isLegendary(),
                    updatedPokemon.get().getAbilities(),
                    updatedPokemon.get().getWeight(),
                    updatedPokemon.get().getHeight(),
                    updatedPokemon.get().getLevel(),
                    updatedPokemon.get().isPublicItem());
            return ResponseEntity.ok(updatedPokemonResponse);
        }else{
            throw new UpdateNotAllowedException("The updated is not possible, please ensure all values are correct");
        }
    }

    @DeleteMapping("/{pokemonId}")
    public ResponseEntity<?> deletePokemon(@PathVariable String pokemonId) {

        final boolean isDeleted = this.deletePokemonInputPort.execute(this.getAuthenticatedEmail(), pokemonId);

        if (isDeleted) {
            return ResponseEntity.ok("Pokemon has been deleted");
        }
        throw new DeleteNotAllowedException("The pokemon could not be deleted");
    }

    private String getAuthenticatedEmail() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }
}
