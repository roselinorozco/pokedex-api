package com.roselinorozco.pokedex.pokemonservice.domain.exception;

/**
 * @author Roselin Orozco
 */
public class DeleteNotAllowedException extends RuntimeException {

    public DeleteNotAllowedException(final String message) {
        super(message);
    }
}
