package com.roselinorozco.pokedex.userservice.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserResponseTest {

    @Test
    public void testEmailField() {

        final String expectedEmail = "user@example.com";

        final UserResponse userResponse = new UserResponse(expectedEmail);

        assertEquals(expectedEmail, userResponse.email(), "The email should match the email passed to the constructor.");
    }
}
