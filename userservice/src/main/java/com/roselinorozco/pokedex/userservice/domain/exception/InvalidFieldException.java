package com.roselinorozco.pokedex.userservice.domain.exception;

/**
 * @author Roselin Orozco
 */
public class InvalidFieldException extends RuntimeException {

    private final String fieldName;

    public InvalidFieldException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
