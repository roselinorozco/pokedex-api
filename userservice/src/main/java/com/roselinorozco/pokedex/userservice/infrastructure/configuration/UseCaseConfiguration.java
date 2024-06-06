package com.roselinorozco.pokedex.userservice.infrastructure.configuration;

import com.roselinorozco.pokedex.userservice.application.usecase.AuthenticateUserUseCase;
import com.roselinorozco.pokedex.userservice.application.usecase.FindUserUseCase;
import com.roselinorozco.pokedex.userservice.application.usecase.GetTokenForUserUseCase;
import com.roselinorozco.pokedex.userservice.application.usecase.RegisterUserUseCase;
import com.roselinorozco.pokedex.userservice.application.validation.AuthValidationService;
import com.roselinorozco.pokedex.userservice.domain.port.out.UserRepositoryOutputPort;
import com.roselinorozco.pokedex.userservice.domain.port.service.PasswordManagerServicePort;
import com.roselinorozco.pokedex.userservice.domain.port.service.TokenServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Roselin Orozco
 */
@Configuration
public class UseCaseConfiguration {

    @Bean
    public AuthenticateUserUseCase authenticateUserUseCase(final UserRepositoryOutputPort userRepositoryOutputPort,
                                                           final PasswordManagerServicePort passwordManagerServicePort) {
        return new AuthenticateUserUseCase(
                userRepositoryOutputPort,
                passwordManagerServicePort,
                new AuthValidationService()
        );
    }

    @Bean
    public FindUserUseCase findUserUseCase(final UserRepositoryOutputPort userRepositoryOutputPort) {
        return new FindUserUseCase(userRepositoryOutputPort);
    }

    @Bean
    public GetTokenForUserUseCase getTokenForUserUseCase(final TokenServicePort tokenServicePort) {
        return new GetTokenForUserUseCase(tokenServicePort);
    }

    @Bean
    public RegisterUserUseCase RegisterUserUseCase(final UserRepositoryOutputPort userRepositoryOutputPort,
                                                   final PasswordManagerServicePort passwordManagerServicePort) {

        return new RegisterUserUseCase(
                userRepositoryOutputPort,
                passwordManagerServicePort,
                new AuthValidationService()
        );
    }
}
