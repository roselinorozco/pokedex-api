package com.roselinorozco.pokedex.pokemonservice.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Roselin Orozco
 */
public class PokemonTest {

    private Pokemon pokemon;

    @BeforeEach
    void setUp() {
        pokemon = new Pokemon("1", "Bulbasaur", "Grass", false, "Overgrow",
                69, 7, 5, true, "ash@example.com");
    }

    @Test
    void testDefaultConstructor() {
        Pokemon defaultPokemon = new Pokemon();
        assertNull(defaultPokemon.getName());
    }

    @Test
    void testFullConstructor() {
        assertEquals("1", pokemon.getId());
        assertEquals("Bulbasaur", pokemon.getName());
        assertEquals("Grass", pokemon.getType());
        assertFalse(pokemon.isLegendary());
        assertEquals("Overgrow", pokemon.getAbilities());
        assertEquals(69, pokemon.getWeight());
        assertEquals(7, pokemon.getHeight());
        assertEquals(5, pokemon.getLevel());
        assertTrue(pokemon.isPublicItem());
        assertEquals("ash@example.com", pokemon.getOwner());
    }

    @Test
    void testSetId() {
        pokemon.setId("2");
        assertEquals("2", pokemon.getId());
    }

    @Test
    void testSetName() {
        pokemon.setName("Ivysaur");
        assertEquals("Ivysaur", pokemon.getName());
    }

    @Test
    void testSetType() {
        pokemon.setType("Poison");
        assertEquals("Poison", pokemon.getType());
    }

    @Test
    void testSetAbilities() {
        pokemon.setAbilities("Chlorophyll");
        assertEquals("Chlorophyll", pokemon.getAbilities());
    }

    @Test
    void testSetWeight() {
        pokemon.setWeight(76);
        assertEquals(76, pokemon.getWeight());
    }

    @Test
    void testSetHeight() {
        pokemon.setHeight(10);
        assertEquals(10, pokemon.getHeight());
    }

    @Test
    void testSetLevel() {
        pokemon.setLevel(15);
        assertEquals(15, pokemon.getLevel());
    }

    @Test
    void testSetOwner() {
        pokemon.setOwner("Misty");
        assertEquals("Misty", pokemon.getOwner());
    }

    @Test
    void testSetLegendary() {
        pokemon.setLegendary(true);
        assertTrue(pokemon.isLegendary());
    }

    @Test
    void testSetPublicItem() {
        pokemon.setPublicItem(false);
        assertFalse(pokemon.isPublicItem());
    }
}
