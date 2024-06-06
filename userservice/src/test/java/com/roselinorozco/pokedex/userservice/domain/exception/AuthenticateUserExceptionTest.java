package com.roselinorozco.pokedex.userservice.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AuthenticateUserExceptionTest {
    @Test
    public void testExceptionMessage() {
        String expectedMessage = "Failed to authenticate user";
        AuthenticateUserException exception = new AuthenticateUserException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }
}
