package com.roselinorozco.pokedex.pokemonservice.domain.exception;

/**
 * @author Roselin Orozco
 */
public class PokemonBelongToOtherOwnerException extends RuntimeException {
    public PokemonBelongToOtherOwnerException(final String message) {
        super(message);
    }
}
