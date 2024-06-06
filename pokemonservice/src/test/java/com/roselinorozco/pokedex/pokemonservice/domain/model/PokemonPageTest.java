package com.roselinorozco.pokedex.pokemonservice.domain.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Roselin Orozco
 */
public class PokemonPageTest {

    @Test
    void testCreateEmptyPokemonPage() {
        PokemonPage emptyPage = new PokemonPage(List.of(), 0, 0);
        assertTrue(emptyPage.pokemons().isEmpty());
        assertEquals(0, emptyPage.totalPages());
        assertEquals(0, emptyPage.totalElements());
    }

    @Test
    void testCreateNonEmptyPokemonPage() {

        final Pokemon bulbasaur = new Pokemon("1", "Bulbasaur", "Grass", false, "Overgrow",
                69, 7, 5, true, "ash@example.com");
        final Pokemon charmander = new Pokemon("4", "Charmander", "Fire", false, "Blaze",
                85, 6, 5, false, "brock@example.com");
        final List<Pokemon> pokemonList = List.of(bulbasaur, charmander);

        final PokemonPage page = new PokemonPage(pokemonList, 1, 2);

        assertEquals(2, page.pokemons().size());
        assertEquals(1, page.totalPages());
        assertEquals(2, page.totalElements());
        assertEquals(bulbasaur, page.pokemons().get(0));
        assertEquals(charmander, page.pokemons().get(1));
    }

    @Test
    void testEquality() {

        final Pokemon bulbasaur = new Pokemon("1", "Bulbasaur", "Grass", false, "Overgrow",
                69, 7, 5, true, "ash@example.com");
        final List<Pokemon> pokemonList1 = List.of(bulbasaur);
        final List<Pokemon> pokemonList2 = List.of(bulbasaur);

        final PokemonPage page1 = new PokemonPage(pokemonList1, 1, 1);
        final PokemonPage page2 = new PokemonPage(pokemonList2, 1, 1);

        assertEquals(page1, page2);
    }

    @Test
    void testNotEqual() {

        final Pokemon bulbasaur = new Pokemon("1", "Bulbasaur", "Grass", false, "Overgrow",
                69, 7, 5, true, "ash@example.com");
        final Pokemon charmander = new Pokemon("4", "Charmander", "Fire", false, "Blaze",
                85, 6, 5, false, "brock@example.com");
        final List<Pokemon> pokemonList1 = List.of(bulbasaur);
        final List<Pokemon> pokemonList2 = List.of(charmander);

        final PokemonPage page1 = new PokemonPage(pokemonList1, 1, 1);
        final PokemonPage page2 = new PokemonPage(pokemonList2, 1, 1);

        assertNotEquals(page1, page2);
    }
}
