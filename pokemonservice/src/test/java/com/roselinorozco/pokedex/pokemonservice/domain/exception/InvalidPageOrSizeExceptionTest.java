package com.roselinorozco.pokedex.pokemonservice.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Roselin Orozco
 */
public class InvalidPageOrSizeExceptionTest {

    @Test
    void testExceptionMessage() {
        final String testMessage = "Page and size must be provided";
        final InvalidPageOrSizeException exception = new InvalidPageOrSizeException(testMessage);

        assertEquals(testMessage, exception.getMessage());
    }
}
