package com.roselinorozco.pokedex.pokemonservice.domain.port.in;

import com.roselinorozco.pokedex.pokemonservice.domain.model.PokemonPage;

/**
 * @author Roselin Orozco
 */
public interface GetPublicPokemonsInputPort {
    PokemonPage execute(Integer page, Integer size);
}
