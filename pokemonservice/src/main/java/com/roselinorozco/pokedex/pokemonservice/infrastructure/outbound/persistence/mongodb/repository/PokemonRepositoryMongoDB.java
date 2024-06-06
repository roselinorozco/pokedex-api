package com.roselinorozco.pokedex.pokemonservice.infrastructure.outbound.persistence.mongodb.repository;

import com.roselinorozco.pokedex.pokemonservice.infrastructure.outbound.persistence.mongodb.entity.PokemonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Roselin Orozco
 */
@Repository
public interface PokemonRepositoryMongoDB extends MongoRepository<PokemonEntity, Integer> {

    Optional<Page<PokemonEntity>> findByOwner(String ownerID, Pageable pageable);
    Optional<Page<PokemonEntity>> findByPublicItem(boolean isPublic, Pageable pageable);
    Optional<PokemonEntity> findById(String id);
    Optional<PokemonEntity> findByIdAndOwner(String id, String owner);
}
