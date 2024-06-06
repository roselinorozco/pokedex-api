package com.roselinorozco.pokedex.userservice.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {

        final String expectedMessage = "User with specified email does not exist";

        final UserNotFoundException exception = new UserNotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage(), "The message should accurately reflect what was provided at instantiation.");
    }
}
