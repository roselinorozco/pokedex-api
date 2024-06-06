package com.roselinorozco.pokedex.userservice.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenerateTokenExceptionTest {

    @Test
    public void testExceptionMessage() {
        final String expectedMessage = "Error generating token";

        final GenerateTokenException exception = new GenerateTokenException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }
}
