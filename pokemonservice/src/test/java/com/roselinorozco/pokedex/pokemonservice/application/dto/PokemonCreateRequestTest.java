package com.roselinorozco.pokedex.pokemonservice.application.dto;

import com.roselinorozco.pokedex.pokemonservice.domain.model.Pokemon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Roselin Orozco
 */
public class PokemonCreateRequestTest {

    @Test
    public void testToPokemon() {
        final PokemonCreateRequest request = new PokemonCreateRequest();
        request.setName("Pikachu");
        request.setType("Electric");
        request.setLegendary(false);
        request.setAbilities("Static");
        request.setWeight(60);
        request.setHeight(40);
        request.setLevel(5);
        request.setPublicItem(true);

        final Pokemon pokemon = request.toPokemon();

        assertEquals("Pikachu", pokemon.getName());
        assertEquals("Electric", pokemon.getType());
        assertFalse(pokemon.isLegendary());
        assertEquals("Static", pokemon.getAbilities());
        assertEquals(60, pokemon.getWeight());
        assertEquals(40, pokemon.getHeight());
        assertEquals(5, pokemon.getLevel());
        assertTrue(pokemon.isPublicItem());
    }

    @Test
    public void testGettersAndSetters() {

        final PokemonCreateRequest request = new PokemonCreateRequest();
        request.setName("Charmander");
        request.setType("Fire");
        request.setLegendary(true);
        request.setAbilities("Blaze");
        request.setWeight(85);
        request.setHeight(60);
        request.setLevel(10);
        request.setPublicItem(false);

        assertEquals("Charmander", request.getName());
        assertEquals("Fire", request.getType());
        assertTrue(request.isLegendary());
        assertEquals("Blaze", request.getAbilities());
        assertEquals(85, request.getWeight());
        assertEquals(60, request.getHeight());
        assertEquals(10, request.getLevel());
        assertFalse(request.isPublicItem());
    }
}
