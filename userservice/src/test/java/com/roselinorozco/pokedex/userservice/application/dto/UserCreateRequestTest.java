package com.roselinorozco.pokedex.userservice.application.dto;

import com.roselinorozco.pokedex.userservice.domain.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserCreateRequestTest {

    @Test
    public void testGetAndSetEmail() {

        final UserCreateRequest request = new UserCreateRequest();
        final String email = "user123@example.com";

        request.setEmail(email);

        assertEquals(email, request.getEmail(), "The email retrieved should match the email set.");
    }

    @Test
    public void testGetAndSetPassword() {

        final UserCreateRequest request = new UserCreateRequest();
        final String password = "12345";

        request.setPassword(password);

        assertEquals(password, request.getPassword(), "The password retrieved should match the password set.");
    }

    @Test
    public void testToUser() {

        final UserCreateRequest request = new UserCreateRequest();
        request.setEmail("user123@example.com");
        request.setPassword("12345");

        final User user = request.toUser();

        assertNotNull(user, "The user object should not be null.");
        assertEquals(request.getEmail(), user.getEmail(), "The email in the user object should match the email set in UserCreateRequest.");
        assertEquals(request.getPassword(), user.getPassword(), "The password in the user object should match the password set in UserCreateRequest.");
    }
}
