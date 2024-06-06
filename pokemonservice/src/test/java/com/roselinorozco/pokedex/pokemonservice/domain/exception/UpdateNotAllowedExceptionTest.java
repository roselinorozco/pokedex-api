package com.roselinorozco.pokedex.pokemonservice.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Roselin Orozco
 */
public class UpdateNotAllowedExceptionTest {

    @Test
    void testExceptionMessage() {

        final String testMessage = "Update not allowed. This Pokémon belongs to another user.";
        final UpdateNotAllowedException exception = new UpdateNotAllowedException(testMessage);

        assertEquals(testMessage, exception.getMessage());
    }
}
