package com.roselinorozco.pokedex.pokemonservice.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Roselin Orozco
 */
public class CreatedPokemonResponseTest {

    @Test
    void testRecordData() {
        final CreatedPokemonResponse response = new CreatedPokemonResponse("1", "Pikachu", "Electric", true, "Static", 60, 4, 5, false, "ash@example.com");

        assertEquals("1", response.id());
        assertEquals("Pikachu", response.name());
        assertEquals("Electric", response.type());
        assertTrue(response.legendary());
        assertEquals("Static", response.abilities());
        assertEquals(60, response.weight());
        assertEquals(4, response.height());
        assertEquals(5, response.level());
        assertFalse(response.publicItem());
        assertEquals("ash@example.com", response.owner());
    }

    @Test
    void testEquality() {
        final CreatedPokemonResponse response1 = new CreatedPokemonResponse("1", "Pikachu", "Electric", true, "Static", 60, 4, 5, false, "ash@example.com");
        final CreatedPokemonResponse response2 = new CreatedPokemonResponse("1", "Pikachu", "Electric", true, "Static", 60, 4, 5, false, "ash@example.com");

        assertEquals(response1, response2);
    }

    @Test
    void testNonEquality() {
        final CreatedPokemonResponse response1 = new CreatedPokemonResponse("1", "Pikachu", "Electric", true, "Static", 60, 4, 5, false, "ash@example.com");
        final CreatedPokemonResponse response2 = new CreatedPokemonResponse("2", "Charizard", "Fire", false, "Blaze", 90, 17, 36, true, "brock@example.com");

        assertNotEquals(response1, response2);
    }
}
