package com.roselinorozco.pokedex.pokemonservice.domain.port.in;

import com.roselinorozco.pokedex.pokemonservice.domain.model.Pokemon;

/**
 * @author Roselin Orozco
 */
public interface RegisterPokemonInputPort {
    Pokemon execute(Pokemon pokemon);
}
