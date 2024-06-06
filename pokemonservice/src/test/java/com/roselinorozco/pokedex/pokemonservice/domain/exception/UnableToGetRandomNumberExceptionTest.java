package com.roselinorozco.pokedex.pokemonservice.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Roselin Orozco
 */
public class UnableToGetRandomNumberExceptionTest {

    @Test
    void testExceptionMessage() {

        final String testMessage = "Error getting random number";
        final UnableToGetRandomNumberException exception = new UnableToGetRandomNumberException(testMessage);

        assertEquals(testMessage, exception.getMessage());
    }
}
