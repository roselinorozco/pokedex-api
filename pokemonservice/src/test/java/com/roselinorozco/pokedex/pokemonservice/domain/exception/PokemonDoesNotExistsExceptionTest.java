package com.roselinorozco.pokedex.pokemonservice.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Roselin Orozco
 */
public class PokemonDoesNotExistsExceptionTest {

    @Test
    void testExceptionMessage() {

        final String testMessage = "The Pokémon with the provided ID is not registered.";
        final PokemonDoesNotExistsException exception = new PokemonDoesNotExistsException(testMessage);

        assertEquals(testMessage, exception.getMessage());
    }
}
