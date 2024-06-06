package com.roselinorozco.pokedex.userservice.infrastructure.service.adapter;

import com.roselinorozco.pokedex.userservice.domain.port.service.PasswordManagerServicePort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Roselin Orozco
 */
@Service
public class BCryptPasswordManagerAdapter implements PasswordManagerServicePort {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    BCryptPasswordManagerAdapter() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String hashPassword(String password) {
        return this.bCryptPasswordEncoder.encode(password);
    }

    @Override
    public boolean matches(String password, String encodedPassword) {
        return this.bCryptPasswordEncoder.matches(password, encodedPassword);
    }
}
