package com.roselinorozco.pokedex.pokemonservice.domain.exception;

/**
 * @author Roselin Orozco
 */
public class PokemonDoesNotExistsException extends RuntimeException {

    public PokemonDoesNotExistsException(final String message) {
        super(message);
    }
}
