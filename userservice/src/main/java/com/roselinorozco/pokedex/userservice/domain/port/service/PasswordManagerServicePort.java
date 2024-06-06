package com.roselinorozco.pokedex.userservice.domain.port.service;

/**
 * @author Roselin Orozco
 */
public interface PasswordManagerServicePort {

    String hashPassword(String password);

    boolean matches(String password, String encodedPassword);
}
