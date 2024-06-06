package com.roselinorozco.pokedex.userservice.infrastructure.outbound.persistence.mongodb.adapter;

import com.roselinorozco.pokedex.userservice.domain.port.out.UserRepositoryOutputPort;
import com.roselinorozco.pokedex.userservice.domain.model.User;
import com.roselinorozco.pokedex.userservice.infrastructure.outbound.persistence.mongodb.entity.UserEntity;
import com.roselinorozco.pokedex.userservice.infrastructure.outbound.persistence.mongodb.repository.UserRepositoryMongoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Roselin Orozco
 */
@Repository
public class UserRepositoryMongoDBAdapter implements UserRepositoryOutputPort {

    private final UserRepositoryMongoDB userRepositoryMongoDB;

    @Autowired
    public UserRepositoryMongoDBAdapter(UserRepositoryMongoDB userRepositoryMongoDB) {
        this.userRepositoryMongoDB = userRepositoryMongoDB;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepositoryMongoDB.findByEmail(email).map(userEntity ->
                new User(userEntity.getEmail(), userEntity.getPassword())
        );
    }

    public User create(User user) {
        UserEntity userEntity = new UserEntity(user.getEmail(), user.getPassword());
        UserEntity createdUserEntity = userRepositoryMongoDB.save(userEntity);
        return new User(createdUserEntity.getEmail(), createdUserEntity.getPassword());
    }
}
