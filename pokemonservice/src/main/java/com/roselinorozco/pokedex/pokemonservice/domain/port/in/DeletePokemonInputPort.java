package com.roselinorozco.pokedex.pokemonservice.domain.port.in;

/**
 * @author Roselin Orozco
 */
public interface DeletePokemonInputPort {
    boolean execute(String owner, String id);
}
