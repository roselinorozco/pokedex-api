package com.roselinorozco.pokedex.pokemonservice.domain.port.in;

import com.roselinorozco.pokedex.pokemonservice.domain.model.Pokemon;
import java.util.Optional;

/**
 * @author Roselin Orozco
 */
public interface UpdatePokemonInputPort {
    Optional<Pokemon> execute(Pokemon pokemon);
}
