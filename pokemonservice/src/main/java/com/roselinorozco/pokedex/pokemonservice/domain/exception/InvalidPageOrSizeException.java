package com.roselinorozco.pokedex.pokemonservice.domain.exception;

/**
 * @author Roselin Orozco
 */
public class InvalidPageOrSizeException extends RuntimeException {
    public InvalidPageOrSizeException(final String message) {
        super(message);
    }
}
