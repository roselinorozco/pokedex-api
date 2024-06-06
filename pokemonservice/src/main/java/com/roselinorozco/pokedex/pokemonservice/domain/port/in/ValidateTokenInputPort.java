package com.roselinorozco.pokedex.pokemonservice.domain.port.in;

import com.roselinorozco.pokedex.pokemonservice.domain.exception.ValidateTokenException;
import com.roselinorozco.pokedex.pokemonservice.domain.model.TokenValidationResult;

/**
 * @author Roselin Orozco
 */
public interface ValidateTokenInputPort {

    TokenValidationResult execute(String token) throws ValidateTokenException;
}
