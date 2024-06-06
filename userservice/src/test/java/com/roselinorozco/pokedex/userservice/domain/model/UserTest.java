package com.roselinorozco.pokedex.userservice.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testUserEmailSetterAndGetter() {

        final User user = new User();
        final String expectedEmail = "test@example.com";

        user.setEmail(expectedEmail);

        assertEquals(expectedEmail, user.getEmail(), "The email returned was not the same as the email set.");
    }

    @Test
    public void testUserPasswordSetterAndGetter() {

        final User user = new User();
        final String expectedPassword = "password123";

        user.setPassword(expectedPassword);

        assertEquals(expectedPassword, user.getPassword(), "The password returned was not the same as the password set.");
    }

    @Test
    public void testUserConstructorWithParameters() {

        final String expectedEmail = "test@example.com";
        final String expectedPassword = "password123";

        final User user = new User(expectedEmail, expectedPassword);

        assertEquals(expectedEmail, user.getEmail(), "Email should match the one set through constructor.");
        assertEquals(expectedPassword, user.getPassword(), "Password should match the one set through constructor.");
    }
}
