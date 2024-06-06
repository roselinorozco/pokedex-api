package com.roselinorozco.pokedex.userservice.application.usecase;

import com.roselinorozco.pokedex.userservice.domain.exception.GenerateTokenException;
import com.roselinorozco.pokedex.userservice.domain.model.Token;
import com.roselinorozco.pokedex.userservice.domain.port.in.GetTokenForUserInputPort;
import com.roselinorozco.pokedex.userservice.domain.port.service.TokenServicePort;

/**
 * @author Roselin Orozco
 */
public class GetTokenForUserUseCase implements GetTokenForUserInputPort {

    private final TokenServicePort tokenServicePort;

    public GetTokenForUserUseCase(final TokenServicePort tokenServicePort) {
        this.tokenServicePort = tokenServicePort;
    }

    @Override
    public Token execute(final String email) throws GenerateTokenException {
        return tokenServicePort.generateTokenForUserId(email);
    }
}
