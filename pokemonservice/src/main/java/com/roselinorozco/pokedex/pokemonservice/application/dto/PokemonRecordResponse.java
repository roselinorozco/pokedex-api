package com.roselinorozco.pokedex.pokemonservice.application.dto;

/**
 * @author Roselin Orozco
 */
public record PokemonRecordResponse(String id, String name, String type, boolean legendary, String abilities, int weight, int height, int level, boolean publicItem, String owner) {}
