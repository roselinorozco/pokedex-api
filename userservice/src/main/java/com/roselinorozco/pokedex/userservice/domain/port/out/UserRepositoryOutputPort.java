package com.roselinorozco.pokedex.userservice.domain.port.out;

import com.roselinorozco.pokedex.userservice.domain.model.User;

import java.util.Optional;

/**
 * @author Roselin Orozco
 */
public interface UserRepositoryOutputPort {

    Optional<User> findByEmail(String email);

    User create(User user);
}
