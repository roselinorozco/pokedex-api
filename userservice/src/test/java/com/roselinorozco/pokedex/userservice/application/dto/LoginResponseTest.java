package com.roselinorozco.pokedex.userservice.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginResponseTest {

    @Test
    public void testLoginResponseAttributes() {

        final String email = "user@example.com";
        final String token = "token123";
        final String expiryTime = "2023-06-30 12:00:00";

        final LoginResponse loginResponse = new LoginResponse(email, token, expiryTime);

        assertEquals(email, loginResponse.email(), "Email should match the provided value.");
        assertEquals(token, loginResponse.token(), "Token should match the provided value.");
        assertEquals(expiryTime, loginResponse.expiryTime(), "Expiry time should match the provided value.");
    }
}
