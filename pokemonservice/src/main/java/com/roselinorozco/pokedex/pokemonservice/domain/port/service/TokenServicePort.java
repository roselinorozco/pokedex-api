package com.roselinorozco.pokedex.pokemonservice.domain.port.service;

import com.roselinorozco.pokedex.pokemonservice.domain.exception.ValidateTokenException;
import com.roselinorozco.pokedex.pokemonservice.domain.model.TokenValidationResult;

/**
 * @author Roselin Orozco
 */
public interface TokenServicePort {

    TokenValidationResult validateToken(String token) throws ValidateTokenException;
}
