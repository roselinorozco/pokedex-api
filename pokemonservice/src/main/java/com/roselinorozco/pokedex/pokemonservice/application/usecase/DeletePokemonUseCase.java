package com.roselinorozco.pokedex.pokemonservice.application.usecase;

import com.roselinorozco.pokedex.pokemonservice.domain.exception.PokemonBelongToOtherOwnerException;
import com.roselinorozco.pokedex.pokemonservice.domain.exception.PokemonDoesNotExistsException;
import com.roselinorozco.pokedex.pokemonservice.domain.port.in.DeletePokemonInputPort;
import com.roselinorozco.pokedex.pokemonservice.domain.port.out.PokemonOutputPort;

/**
 * @author Roselin Orozco
 */
public class DeletePokemonUseCase implements DeletePokemonInputPort {

    private final PokemonOutputPort pokemonOutputPort;

    public DeletePokemonUseCase(final PokemonOutputPort pokemonOutputPort) {
        this.pokemonOutputPort = pokemonOutputPort;
    }

    @Override
    public boolean execute(final String owner, final String id) {

        this.checkPokemonExists(id);
        this.checkPokemonBelongsToOwner(id, owner);
        return this.pokemonOutputPort.delete(id);
    }

    private void checkPokemonExists(final String id) {

        if (this.pokemonOutputPort.findById(id).isEmpty()){
            throw new PokemonDoesNotExistsException("The Pokémon with the provided ID is not registered.");
        }
    }

    private void checkPokemonBelongsToOwner(final String id, final String owner) {

        if (this.pokemonOutputPort.findByIdAndOwner(id, owner).isEmpty()){
            throw new PokemonBelongToOtherOwnerException("Deletion not allowed. This Pokémon belongs to another owner.");
        }
    }
}
