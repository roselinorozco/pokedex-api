package com.roselinorozco.pokedex.pokemonservice.infrastructure.inbound.rest.pokemon.adapter.controller;

import com.roselinorozco.pokedex.pokemonservice.application.dto.PokemonRecordResponse;
import com.roselinorozco.pokedex.pokemonservice.domain.model.PokemonPage;
import com.roselinorozco.pokedex.pokemonservice.domain.port.in.GetPublicPokemonsInputPort;
import com.roselinorozco.pokedex.pokemonservice.infrastructure.mapper.PokemonMapper;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author Roselin Orozco
 */
@RestController
@RequestMapping("/api/public/pokemon")
public class PublicPokemonRestController {

    private final GetPublicPokemonsInputPort getPublicPokemonsInputPort;

    private final PokemonMapper pokemonMapper;

    public PublicPokemonRestController(final GetPublicPokemonsInputPort getPublicPokemonsInputPort,
                                       final PokemonMapper pokemonMapper) {
        this.getPublicPokemonsInputPort = getPublicPokemonsInputPort;
        this.pokemonMapper = pokemonMapper;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getPublicPokemons(@RequestParam(required = false) Integer page,
                                               @RequestParam(required = false) Integer size) {

        final PokemonPage pokemonPage = this.getPublicPokemonsInputPort.execute(page, size);

        final List<PokemonRecordResponse> pokemonRecordResponse =
                pokemonPage.pokemons().stream().map(this.pokemonMapper::convertToPokemonRecordResponse).toList();

        final Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(new PageImpl<>(pokemonRecordResponse, pageable, pokemonPage.totalElements()));
    }
}
