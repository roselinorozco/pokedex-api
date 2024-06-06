package com.roselinorozco.pokedex.pokemonservice.application.dto;

import com.roselinorozco.pokedex.pokemonservice.domain.model.Pokemon;

/**
 * @author Roselin Orozco
 */
public class PokemonUpdateRequest {

    private Boolean publicItem;

    public Pokemon toPokemon() {

        final Pokemon pokemon = new Pokemon();
        pokemon.setPublicItem(this.publicItem);

        return pokemon;
    }

    public Boolean isPublicItem() {
        return publicItem;
    }

    public void setPublicItem(Boolean publicItem) {
        this.publicItem = publicItem;
    }
}
