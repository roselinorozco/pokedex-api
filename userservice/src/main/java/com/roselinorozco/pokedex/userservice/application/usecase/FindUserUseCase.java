package com.roselinorozco.pokedex.userservice.application.usecase;

import com.roselinorozco.pokedex.userservice.domain.model.User;
import com.roselinorozco.pokedex.userservice.domain.port.in.FindUserByEmailInputPort;
import com.roselinorozco.pokedex.userservice.domain.port.out.UserRepositoryOutputPort;

import java.util.Optional;

/**
 * @author Roselin Orozco
 */
public class FindUserUseCase implements FindUserByEmailInputPort {

    private final UserRepositoryOutputPort userRepositoryOutputPort;

    public FindUserUseCase(UserRepositoryOutputPort userRepositoryOutputPort) {
        this.userRepositoryOutputPort = userRepositoryOutputPort;
    }

    @Override
    public Optional<User> execute(final String email) {
        return userRepositoryOutputPort.findByEmail(email);
    }
}
