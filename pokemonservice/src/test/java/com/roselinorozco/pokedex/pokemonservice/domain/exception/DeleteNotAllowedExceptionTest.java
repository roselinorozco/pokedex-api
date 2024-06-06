package com.roselinorozco.pokedex.pokemonservice.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Roselin Orozco
 */
public class DeleteNotAllowedExceptionTest {

    @Test
    void testExceptionMessage() {
        final String testMessage = "Deletion is not allowed.";
        final DeleteNotAllowedException exception = new DeleteNotAllowedException(testMessage);

        assertEquals(testMessage, exception.getMessage());
    }
}
