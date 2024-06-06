package com.roselinorozco.pokedex.userservice.domain.port.in;

import com.roselinorozco.pokedex.userservice.domain.exception.AuthenticateUserException;
import com.roselinorozco.pokedex.userservice.domain.model.User;

/**
 * @author Roselin Orozco
 */
public interface AuthenticateUserInputPort {

    User execute(String email, String password) throws AuthenticateUserException;
}
