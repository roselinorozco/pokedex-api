package com.roselinorozco.pokedex.pokemonservice.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Roselin Orozco
 */
public class RegisterNotAllowedExceptionTest {

    @Test
    void testExceptionMessage() {

        final String testMessage = "Invalid name. Only letters are allowed.";
        final RegisterNotAllowedException exception = new RegisterNotAllowedException(testMessage);

        assertEquals(testMessage, exception.getMessage());
    }
}
