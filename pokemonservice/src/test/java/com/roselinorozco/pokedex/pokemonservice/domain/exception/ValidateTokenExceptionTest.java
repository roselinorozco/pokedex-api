package com.roselinorozco.pokedex.pokemonservice.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Roselin Orozco
 */
public class ValidateTokenExceptionTest {

    @Test
    void testExceptionMessage() {

        final String testMessage = "Token is null or empty";
        final ValidateTokenException exception = new ValidateTokenException(testMessage);

        assertEquals(testMessage, exception.getMessage());
    }
}
