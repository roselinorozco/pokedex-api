package com.roselinorozco.pokedex.pokemonservice.domain.model;

/**
 * @author Roselin Orozco
 */
public class Pokemon {

    private String id;
    private String name;
    private String type;
    private Boolean legendary;
    private String abilities;
    private Integer weight;
    private Integer height;
    private Integer level;
    private Boolean publicItem;
    private String owner;

    public Pokemon() {}

    public Pokemon(String id, String name, String type, Boolean legendary, String abilities,
                   Integer weight, Integer height, Integer level, Boolean publicItem, String owner) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.legendary = legendary;
        this.abilities = abilities;
        this.weight = weight;
        this.height = height;
        this.level = level;
        this.publicItem = publicItem;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Boolean isLegendary() {
        return legendary;
    }

    public void setLegendary(Boolean legendary) {
        this.legendary = legendary;
    }

    public Boolean isPublicItem() {
        return publicItem;
    }

    public void setPublicItem(Boolean publicItem) {
        this.publicItem = publicItem;
    }
}
