package com.roselinorozco.pokedex.userservice.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginRequestTest {

    @Test
    public void testParameterizedConstructor() {

        final String expectedEmail = "user@example.com";
        final String expectedPassword = "password123";

        final LoginRequest loginRequest = new LoginRequest(expectedEmail, expectedPassword);

        assertEquals(expectedEmail, loginRequest.getEmail(), "Email should match the input provided to constructor");
        assertEquals(expectedPassword, loginRequest.getPassword(), "Password should match the input provided to constructor");
    }

    @Test
    public void testSetAndGetEmail() {

        final LoginRequest loginRequest = new LoginRequest();
        final String expectedEmail = "test@example.com";

        loginRequest.setEmail(expectedEmail);

        assertEquals(expectedEmail, loginRequest.getEmail(), "Set email should be retrievable via getEmail");
    }

    @Test
    public void testSetAndGetPassword() {

        final LoginRequest loginRequest = new LoginRequest();
        final String expectedPassword = "password123";

        loginRequest.setPassword(expectedPassword);

        assertEquals(expectedPassword, loginRequest.getPassword(), "Set password should be retrievable via getPassword");
    }
}
