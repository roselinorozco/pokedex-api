package com.roselinorozco.pokedex.pokemonservice.infrastructure.outbound.persistence.mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Roselin Orozco
 */
@Document(collection = "pokemons")
public class PokemonEntity {

    @Id
    private String id;
    private String name;
    private String type;
    private boolean legendary;
    private String abilities;
    private int weight;
    private int height;
    private int level;
    private boolean publicItem;
    private String owner;

    public PokemonEntity() {}

    public PokemonEntity(String name, String type, boolean legendary, String abilities, int weight,
                         int height, int level, boolean publicItem, String ownerID) {

        this.name = name;
        this.type = type;
        this.legendary = legendary;
        this.abilities = abilities;
        this.weight = weight;
        this.height = height;
        this.level = level;
        this.publicItem = publicItem;
        this.owner = ownerID;

    }

    public PokemonEntity(String id, String name, String type, boolean legendary, String abilities, int weight,
                         int height, int level, boolean publicItem, String ownerID) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.legendary = legendary;
        this.abilities = abilities;
        this.weight = weight;
        this.height = height;
        this.level = level;
        this.publicItem = publicItem;
        this.owner = ownerID;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isLegendary() {
        return legendary;
    }

    public void setLegendary(boolean legendary) {
        this.legendary = legendary;
    }

    public boolean isPublicItem() {
        return publicItem;
    }

    public void setPublicItem(boolean publicItem) {
        this.publicItem = publicItem;
    }
}
