package com.roselinorozco.pokedex.pokemonservice.application.usecase;

import com.roselinorozco.pokedex.pokemonservice.domain.exception.InvalidFieldException;
import com.roselinorozco.pokedex.pokemonservice.domain.exception.PokemonDoesNotExistsException;
import com.roselinorozco.pokedex.pokemonservice.domain.exception.UpdateNotAllowedException;
import com.roselinorozco.pokedex.pokemonservice.domain.model.Pokemon;
import com.roselinorozco.pokedex.pokemonservice.domain.port.in.UpdatePokemonInputPort;
import com.roselinorozco.pokedex.pokemonservice.domain.port.out.PokemonOutputPort;

import java.util.Optional;

/**
 * @author Roselin Orozco
 */
public class UpdatePokemonUseCase implements UpdatePokemonInputPort {

    private final PokemonOutputPort pokemonOutputPort;

    public UpdatePokemonUseCase(final PokemonOutputPort pokemonOutputPort) {
        this.pokemonOutputPort = pokemonOutputPort;
    }

    @Override
    public Optional<Pokemon> execute(final Pokemon pokemon) {

        this.checkBoolean(pokemon.isPublicItem());
        this.checkPokemonExists(pokemon.getId());
        this.checkPokemonBelongsToOwner(pokemon.getId(), pokemon.getOwner());
        return this.pokemonOutputPort.update(pokemon);
    }

    private void checkPokemonExists(final String id) {

        if(this.pokemonOutputPort.findById(id).isEmpty()) {
            throw new PokemonDoesNotExistsException("The Pokémon with the provided ID is not registered.");
        }
    }

    private void checkPokemonBelongsToOwner(final String id, final String owner) {

        if(this.pokemonOutputPort.findByIdAndOwner(id, owner).isEmpty()){
            throw new UpdateNotAllowedException("Update not allowed. This Pokémon belongs to another user.");
        }
    }

    private void checkBoolean(final Boolean isPublic) {

        if(isPublic == null){
            throw new InvalidFieldException("publicItem","Update not allowed. The publicItem value is required.");
        }
    }
}
