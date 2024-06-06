package com.roselinorozco.pokedex.pokemonservice.application.dto;

import com.roselinorozco.pokedex.pokemonservice.domain.model.Pokemon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Roselin Orozco
 */
public class PokemonUpdateRequestTest {

    @Test
    public void testToPokemon() {

        final PokemonUpdateRequest request = new PokemonUpdateRequest();
        request.setPublicItem(true);

        final Pokemon pokemon = request.toPokemon();

        assertTrue(pokemon.isPublicItem());
    }

    @Test
    public void testGettersAndSetters() {

        final PokemonUpdateRequest request = new PokemonUpdateRequest();
        request.setPublicItem(false);

        assertFalse(request.isPublicItem());
    }
}
