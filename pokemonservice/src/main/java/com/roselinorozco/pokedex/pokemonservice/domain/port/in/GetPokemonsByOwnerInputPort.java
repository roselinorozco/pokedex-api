package com.roselinorozco.pokedex.pokemonservice.domain.port.in;

import com.roselinorozco.pokedex.pokemonservice.domain.model.PokemonPage;

/**
 * @author Roselin Orozco
 */
public interface GetPokemonsByOwnerInputPort {
    PokemonPage execute(String ownerID, Integer page, Integer size);
}
