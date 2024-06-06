package com.roselinorozco.pokedex.userservice.domain.exception;

/**
 * @author Roselin Orozco
 */
public class EmailIsAlreadyRegisteredException extends RuntimeException {

    public EmailIsAlreadyRegisteredException(final String email) {
        super("Email " + email + " is already registered");
    }
}
