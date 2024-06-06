package com.roselinorozco.pokedex.userservice.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidPasswordExceptionTest {

    @Test
    public void testExceptionMessage() {
        final String expectedMessage = "Password does not meet complexity requirements";

        final InvalidPasswordException exception = new InvalidPasswordException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage(), "The message should accurately reflect what was provided at instantiation.");
    }
}
