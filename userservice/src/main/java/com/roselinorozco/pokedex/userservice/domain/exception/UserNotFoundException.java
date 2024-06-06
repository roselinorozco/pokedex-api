package com.roselinorozco.pokedex.userservice.domain.exception;

/**
 * @author Roselin Orozco
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final String message) {
        super(message);
    }
}
