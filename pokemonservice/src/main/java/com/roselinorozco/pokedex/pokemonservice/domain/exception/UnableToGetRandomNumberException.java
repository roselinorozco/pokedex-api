package com.roselinorozco.pokedex.pokemonservice.domain.exception;

/**
 * @author Roselin Orozco
 */
public class UnableToGetRandomNumberException extends RuntimeException {
    public UnableToGetRandomNumberException(String message) {
        super(message);
    }
}
