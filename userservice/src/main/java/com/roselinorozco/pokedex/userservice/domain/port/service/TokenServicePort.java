package com.roselinorozco.pokedex.userservice.domain.port.service;

import com.roselinorozco.pokedex.userservice.domain.exception.GenerateTokenException;
import com.roselinorozco.pokedex.userservice.domain.model.Token;

/**
 * @author Roselin Orozco
 */
public interface TokenServicePort {

    Token generateTokenForUserId(String userId) throws GenerateTokenException;
}
