package com.roselinorozco.pokedex.pokemonservice.application.usecase;

import com.roselinorozco.pokedex.pokemonservice.domain.exception.ValidateTokenException;
import com.roselinorozco.pokedex.pokemonservice.domain.model.TokenValidationResult;
import com.roselinorozco.pokedex.pokemonservice.domain.port.in.ValidateTokenInputPort;
import com.roselinorozco.pokedex.pokemonservice.domain.port.service.TokenServicePort;

/**
 * @author Roselin Orozco
 */
public class ValidateTokenUseCase implements ValidateTokenInputPort {

    private final TokenServicePort tokenServicePort;

    public ValidateTokenUseCase(final TokenServicePort tokenServicePort) {
        this.tokenServicePort = tokenServicePort;
    }

    @Override
    public TokenValidationResult execute(String token) throws ValidateTokenException {
        return tokenServicePort.validateToken(token);
    }
}
