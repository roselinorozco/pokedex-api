package com.roselinorozco.pokedex.pokemonservice.infrastructure.outbound.persistence.mongodb.adapter;

import com.roselinorozco.pokedex.pokemonservice.domain.model.Pokemon;
import com.roselinorozco.pokedex.pokemonservice.domain.model.PokemonPage;
import com.roselinorozco.pokedex.pokemonservice.domain.port.out.PokemonOutputPort;
import com.roselinorozco.pokedex.pokemonservice.infrastructure.mapper.PokemonMapper;
import com.roselinorozco.pokedex.pokemonservice.infrastructure.outbound.persistence.mongodb.entity.PokemonEntity;
import com.roselinorozco.pokedex.pokemonservice.infrastructure.outbound.persistence.mongodb.repository.PokemonRepositoryMongoDB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Roselin Orozco
 */
@Repository
public class PokemonMongoDBAdapter implements PokemonOutputPort {

    private final PokemonRepositoryMongoDB pokemonRepositoryMongoDB;
    private final PokemonMapper pokemonMapper;
    private final MongoTemplate mongoTemplate;

    public PokemonMongoDBAdapter(final PokemonRepositoryMongoDB pokemonRepositoryMongoDB,
                                 final PokemonMapper pokemonMapper,
                                 final MongoTemplate mongoTemplate) {
        this.pokemonRepositoryMongoDB = pokemonRepositoryMongoDB;
        this.pokemonMapper = pokemonMapper;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Pokemon create(final Pokemon pokemon) {
        PokemonEntity pokemonEntity = new PokemonEntity(
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

        final PokemonEntity createdPokemonEntity = this.pokemonRepositoryMongoDB.save(pokemonEntity);

        return new Pokemon(
                createdPokemonEntity.getId(),
                createdPokemonEntity.getName(),
                createdPokemonEntity.getType(),
                createdPokemonEntity.isLegendary(),
                createdPokemonEntity.getAbilities(),
                createdPokemonEntity.getWeight(),
                createdPokemonEntity.getHeight(),
                createdPokemonEntity.getLevel(),
                createdPokemonEntity.isPublicItem(),
                createdPokemonEntity.getOwner()
        );
    }

    @Override
    public boolean delete(String id) {
        final Optional<PokemonEntity> pokemonEntity = this.pokemonRepositoryMongoDB.findById(id);

        if (pokemonEntity.isPresent()) {
            this.pokemonRepositoryMongoDB.delete(pokemonEntity.get());
        }

        return this.pokemonRepositoryMongoDB.findById(id).isEmpty();
    }

    @Override
    public PokemonPage findPokemonsByOwner(String ownerID, int page, int size) {

        final Optional<Page<PokemonEntity>> pagePokemonEntities =
                this.pokemonRepositoryMongoDB.findByOwner(ownerID, PageRequest.of(page, size));

        return generatePokemonPage(pagePokemonEntities);
    }

    @Override
    public PokemonPage findPublicPokemons(int page, int size) {

        final Optional<Page<PokemonEntity>> pagePokemonEntities =
                this.pokemonRepositoryMongoDB.findByPublicItem(true, PageRequest.of(page, size));

        return generatePokemonPage(pagePokemonEntities);
    }

    @Override
    public Optional<Pokemon> update(Pokemon pokemon) {

        final Query query = new Query(Criteria.where("id").is(pokemon.getId()).and("owner").is(pokemon.getOwner()));

        final Update update = new Update().set("publicItem", pokemon.isPublicItem());

        final FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        final PokemonEntity updatedPokemonEntity = this.mongoTemplate.findAndModify(query, update, options, PokemonEntity.class);

        return Optional.ofNullable(this.pokemonMapper.convertToPokemon(updatedPokemonEntity));
    }

    @Override
    public Optional<Pokemon> findById(final String id) {
        return this.pokemonRepositoryMongoDB.findById(id).map(this.pokemonMapper::convertToPokemon);
    }

    @Override
    public Optional<Pokemon> findByIdAndOwner(final String id, final String owner) {
        return this.pokemonRepositoryMongoDB.findByIdAndOwner(id, owner).map(this.pokemonMapper::convertToPokemon);
    }

    private PokemonPage generatePokemonPage(final Optional<Page<PokemonEntity>> pagePokemonEntities) {

        final List<Pokemon> pokemons = pagePokemonEntities.map(pokemonPage ->
                pokemonPage.getContent().stream()
                        .map(this.pokemonMapper::convertToPokemon)
                        .toList()
        ).orElse(Collections.emptyList());

        final int totalPages = pagePokemonEntities.map(Page::getTotalPages).orElse(0);
        final long totalElements = pagePokemonEntities.map(Page::getTotalElements).orElse(0l);

        final PokemonPage pokemonPage = new PokemonPage(pokemons, totalPages, totalElements);

        return pokemonPage;
    }
}
