package com.roselinorozco.pokedex.userservice.domain.port.in;

import com.roselinorozco.pokedex.userservice.domain.exception.EmailIsAlreadyRegisteredException;
import com.roselinorozco.pokedex.userservice.domain.exception.InvalidFieldException;
import com.roselinorozco.pokedex.userservice.domain.exception.InvalidPasswordException;
import com.roselinorozco.pokedex.userservice.domain.model.User;

/**
 * @author Roselin Orozco
 */
public interface RegisterUserInputPort {

    User execute(User user) throws InvalidFieldException, InvalidPasswordException, EmailIsAlreadyRegisteredException;
}
