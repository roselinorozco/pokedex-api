package com.roselinorozco.pokedex.pokemonservice.domain.model;

import java.util.List;

/**
 * @author Roselin Orozco
 */
public record PokemonPage(List<Pokemon> pokemons, int totalPages, long totalElements) {}
