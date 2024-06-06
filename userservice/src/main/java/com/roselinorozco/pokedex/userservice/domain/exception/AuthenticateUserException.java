package com.roselinorozco.pokedex.userservice.domain.exception;

/**
 * @author Roselin Orozco
 */
public class AuthenticateUserException extends Exception {
    public AuthenticateUserException(final String message) {
        super(message);
    }
}
