package com.roselinorozco.pokedex.userservice.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailIsAlreadyRegisteredExceptionTest {

    @Test
    public void testExceptionMessageWithValidEmail() {

        final String testEmail = "test@example.com";
        final String expectedMessage = "Email " + testEmail + " is already registered";

        EmailIsAlreadyRegisteredException exception = new EmailIsAlreadyRegisteredException(testEmail);

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testExceptionMessageWithEmptyEmail() {

        final String testEmail = "";
        final String expectedMessage = "Email " + testEmail + " is already registered";

        EmailIsAlreadyRegisteredException exception = new EmailIsAlreadyRegisteredException(testEmail);

        assertEquals(expectedMessage, exception.getMessage());
    }
}
