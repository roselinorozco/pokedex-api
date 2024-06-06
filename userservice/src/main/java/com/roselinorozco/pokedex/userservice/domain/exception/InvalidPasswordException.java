package com.roselinorozco.pokedex.userservice.domain.exception;

/**
 * @author Roselin Orozco
 */
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message) {
        super(message);
    }
}
