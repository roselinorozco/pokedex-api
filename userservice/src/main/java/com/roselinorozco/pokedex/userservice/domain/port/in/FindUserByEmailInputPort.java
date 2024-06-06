package com.roselinorozco.pokedex.userservice.domain.port.in;

import com.roselinorozco.pokedex.userservice.domain.model.User;

import java.util.Optional;

/**
 * @author Roselin Orozco
 */
public interface FindUserByEmailInputPort {

    Optional<User> execute(String email);
}
