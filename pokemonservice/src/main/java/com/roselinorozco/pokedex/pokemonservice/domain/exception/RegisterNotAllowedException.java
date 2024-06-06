package com.roselinorozco.pokedex.pokemonservice.domain.exception;

/**
 * @author Roselin Orozco
 */
public class RegisterNotAllowedException extends RuntimeException {

    public RegisterNotAllowedException(String message) {
        super(message);
    }
}
