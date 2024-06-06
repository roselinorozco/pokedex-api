package com.roselinorozco.pokedex.pokemonservice.application.dto;

import com.roselinorozco.pokedex.pokemonservice.domain.model.Pokemon;


/**
 * @author Roselin Orozco
 */
public class PokemonCreateRequest {

    private String name;
    private String type;
    private Boolean Legendary;
    private String abilities;
    private Integer weight;
    private Integer height;
    private Integer level;
    private Boolean publicItem;

    public Pokemon toPokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(this.name);
        pokemon.setType(this.type);
        pokemon.setLegendary(this.Legendary);
        pokemon.setAbilities(this.abilities);
        pokemon.setWeight(this.weight);
        pokemon.setHeight(this.height);
        pokemon.setLevel(this.level);
        pokemon.setPublicItem(this.publicItem);

        return pokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean isLegendary() {
        return Legendary;
    }

    public void setLegendary(Boolean legendary) {
        Legendary = legendary;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Boolean isPublicItem() {
        return publicItem;
    }

    public void setPublicItem(Boolean publicItem) {
        this.publicItem = publicItem;
    }
}
