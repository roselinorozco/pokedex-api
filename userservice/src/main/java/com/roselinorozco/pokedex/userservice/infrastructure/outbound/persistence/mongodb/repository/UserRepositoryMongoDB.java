package com.roselinorozco.pokedex.userservice.infrastructure.outbound.persistence.mongodb.repository;

import com.roselinorozco.pokedex.userservice.infrastructure.outbound.persistence.mongodb.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Roselin Orozco
 */
@Repository
public interface UserRepositoryMongoDB extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);
}
