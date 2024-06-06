package com.roselinorozco.pokedex.pokemonservice.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Roselin Orozco
 */
public class PokemonRecordResponseTest {

    @Test
    public void testConstructorAndGetters() {
        PokemonRecordResponse response = new PokemonRecordResponse(
                "123",
                "Pikachu",
                "Electric",
                false,
                "Static",
                60,
                40,
                5,
                true,
                "ash@example.com"
        );

        assertEquals("123", response.id());
        assertEquals("Pikachu", response.name());
        assertEquals("Electric", response.type());
        assertFalse(response.legendary());
        assertEquals("Static", response.abilities());
        assertEquals(60, response.weight());
        assertEquals(40, response.height());
        assertEquals(5, response.level());
        assertTrue(response.publicItem());
        assertEquals("ash@example.com", response.owner());
    }
}
