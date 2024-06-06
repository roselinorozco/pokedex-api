package com.roselinorozco.pokedex.userservice.application.usecase;

import com.roselinorozco.pokedex.userservice.application.validation.AuthValidationService;
import com.roselinorozco.pokedex.userservice.domain.exception.AuthenticateUserException;
import com.roselinorozco.pokedex.userservice.domain.model.User;
import com.roselinorozco.pokedex.userservice.domain.port.in.AuthenticateUserInputPort;
import com.roselinorozco.pokedex.userservice.domain.port.out.UserRepositoryOutputPort;
import com.roselinorozco.pokedex.userservice.domain.port.service.PasswordManagerServicePort;

/**
 * @author Roselin Orozco
 */
public class AuthenticateUserUseCase implements AuthenticateUserInputPort {

    private final UserRepositoryOutputPort userRepositoryOutputPort;

    private final PasswordManagerServicePort passwordManagerServicePort;

    private final AuthValidationService authValidationService;

    public AuthenticateUserUseCase(final UserRepositoryOutputPort userRepositoryOutputPort,
                                   final PasswordManagerServicePort passwordManagerServicePort,
                                   final AuthValidationService authValidationService) {
        this.userRepositoryOutputPort = userRepositoryOutputPort;
        this.passwordManagerServicePort = passwordManagerServicePort;
        this.authValidationService = authValidationService;
    }

    @Override
    public User execute(final String email, final String password) throws AuthenticateUserException {

        try {
            this.authValidationService.validate(email, password);
        } catch(RuntimeException exception) {
            throw new AuthenticateUserException(exception.getMessage());
        }

        final User user = userRepositoryOutputPort.findByEmail(email)
                .orElseThrow(() -> new AuthenticateUserException("User by email " + email + " not found"));

        if (!this.passwordManagerServicePort.matches(password, user.getPassword())) {
            throw new AuthenticateUserException("Password does not match for email " + email);
        }

        return user;
    }
}
