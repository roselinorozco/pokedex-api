package com.roselinorozco.pokedex.userservice.domain.port.in;

import com.roselinorozco.pokedex.userservice.domain.exception.GenerateTokenException;
import com.roselinorozco.pokedex.userservice.domain.model.Token;

/**
 * @author Roselin Orozco
 */
public interface GetTokenForUserInputPort {
    Token execute(String email) throws GenerateTokenException;
}
