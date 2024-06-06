package com.roselinorozco.pokedex.pokemonservice.domain.exception;

/**
 * @author Roselin Orozco
 */
public class UpdateNotAllowedException extends RuntimeException {

    public UpdateNotAllowedException(String message) {
        super(message);
    }
}

