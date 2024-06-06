package com.roselinorozco.pokedex.pokemonservice.domain.port.out;

import com.roselinorozco.pokedex.pokemonservice.domain.model.Pokemon;
import com.roselinorozco.pokedex.pokemonservice.domain.model.PokemonPage;
import java.util.Optional;

/**
 * @author Roselin Orozco
 */
public interface PokemonOutputPort {

    Pokemon create(Pokemon pokemon);

    boolean delete(String id);

    PokemonPage findPokemonsByOwner(String ownerID, int page, int size);

    PokemonPage findPublicPokemons(int page, int size);

    Optional<Pokemon>  update(Pokemon pokemon);

    Optional<Pokemon> findById(String id);

    Optional<Pokemon> findByIdAndOwner(String id, String owner);
}
