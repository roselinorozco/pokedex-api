package com.roselinorozco.pokedex.pokemonservice.infrastructure.mapper;

import com.roselinorozco.pokedex.pokemonservice.application.dto.CreatedPokemonResponse;
import com.roselinorozco.pokedex.pokemonservice.application.dto.PokemonRecordResponse;
import com.roselinorozco.pokedex.pokemonservice.application.dto.PokemonUpdateRequest;
import com.roselinorozco.pokedex.pokemonservice.application.dto.PokemonCreateRequest;
import com.roselinorozco.pokedex.pokemonservice.domain.model.Pokemon;
import com.roselinorozco.pokedex.pokemonservice.infrastructure.outbound.persistence.mongodb.entity.PokemonEntity;
import org.springframework.stereotype.Service;

/**
 * @author Roselin Orozco
 */
@Service
public class PokemonMapper {

    public Pokemon convertToPokemon(PokemonEntity pokemonEntity) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonEntity.getId());
        pokemon.setName(pokemonEntity.getName());
        pokemon.setType(pokemonEntity.getType());
        pokemon.setLegendary(pokemonEntity.isLegendary());
        pokemon.setAbilities(pokemonEntity.getAbilities());
        pokemon.setPublicItem(pokemonEntity.isPublicItem());
        pokemon.setOwner(pokemonEntity.getOwner());
        pokemon.setWeight(pokemonEntity.getWeight());
        pokemon.setHeight(pokemonEntity.getHeight());
        pokemon.setLevel(pokemonEntity.getLevel());
        return pokemon;
    }

    public PokemonRecordResponse convertToPokemonRecordResponse(Pokemon pokemon) {
        PokemonRecordResponse pokemonRecordResponse = new PokemonRecordResponse(
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getType(),
                pokemon.isLegendary(),
                pokemon.getAbilities(),
                pokemon.getWeight(),
                pokemon.getHeight(),
                pokemon.getLevel(),
                pokemon.isPublicItem(),
                pokemon.getOwner()
        );

        return pokemonRecordResponse;
    }

    public Pokemon convertToPokemon(PokemonCreateRequest pokemonCreateRequest){

        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonCreateRequest.getName());
        pokemon.setType(pokemonCreateRequest.getType());
        pokemon.setLegendary(pokemonCreateRequest.isLegendary());
        pokemon.setAbilities(pokemonCreateRequest.getAbilities());
        pokemon.setLevel(pokemonCreateRequest.getLevel());
        pokemon.setWeight(pokemonCreateRequest.getWeight());
        pokemon.setHeight((pokemonCreateRequest.getHeight()));
        pokemon.setPublicItem(pokemonCreateRequest.isPublicItem());

        return pokemon;
    }

    public Pokemon convertToPokemon(PokemonUpdateRequest pokemonUpdateRequest, String pokemonId, String owner){

        Pokemon pokemon = new Pokemon();
        pokemon.setPublicItem(pokemonUpdateRequest.isPublicItem());
        pokemon.setId(pokemonId);
        pokemon.setOwner(owner);

        return pokemon;
    }

    public CreatedPokemonResponse convertToCreatedPokemonResponse(Pokemon pokemon){
        return new CreatedPokemonResponse(
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getType(),
                pokemon.isLegendary(),
                pokemon.getAbilities(),
                pokemon.getWeight(),
                pokemon.getHeight(),
                pokemon.getLevel(),
                pokemon.isPublicItem(),
                pokemon.getOwner()
        );
    }
}
