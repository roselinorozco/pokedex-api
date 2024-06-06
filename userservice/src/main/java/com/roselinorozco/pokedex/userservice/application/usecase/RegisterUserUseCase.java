package com.roselinorozco.pokedex.userservice.application.usecase;

import com.roselinorozco.pokedex.userservice.application.validation.AuthValidationService;
import com.roselinorozco.pokedex.userservice.domain.port.in.RegisterUserInputPort;
import com.roselinorozco.pokedex.userservice.domain.port.out.UserRepositoryOutputPort;
import com.roselinorozco.pokedex.userservice.domain.exception.EmailIsAlreadyRegisteredException;
import com.roselinorozco.pokedex.userservice.domain.exception.InvalidFieldException;
import com.roselinorozco.pokedex.userservice.domain.exception.InvalidPasswordException;
import com.roselinorozco.pokedex.userservice.domain.model.User;
import com.roselinorozco.pokedex.userservice.domain.port.service.PasswordManagerServicePort;

/**
 * @author Roselin Orozco
 */
public class RegisterUserUseCase implements RegisterUserInputPort {

    private final UserRepositoryOutputPort userRepositoryOutputPort;
    private final PasswordManagerServicePort passwordManagerServicePort;
    private final AuthValidationService authValidationService;

    public RegisterUserUseCase(final UserRepositoryOutputPort userRepositoryOutputPort,
                               final PasswordManagerServicePort passwordManagerServicePort,
                               final AuthValidationService authValidationService) {
        this.userRepositoryOutputPort = userRepositoryOutputPort;
        this.passwordManagerServicePort = passwordManagerServicePort;
        this.authValidationService = authValidationService;
    }

    @Override
    public User execute(final User user) throws InvalidFieldException, InvalidPasswordException, EmailIsAlreadyRegisteredException {
        this.check(user);

        final User hashedPasswordUser = new User(
                user.getEmail(),
                this.passwordManagerServicePort.hashPassword(user.getPassword()
                ));

        final User savedUser = this.userRepositoryOutputPort.create(hashedPasswordUser);

        return savedUser;
    }

    private void check(final User user) {

        this.authValidationService.validate(user.getEmail(), user.getPassword());

        if (userRepositoryOutputPort.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailIsAlreadyRegisteredException(user.getEmail());
        }
    }
}
