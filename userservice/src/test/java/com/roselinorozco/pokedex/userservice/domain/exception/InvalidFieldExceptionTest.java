package com.roselinorozco.pokedex.userservice.domain.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidFieldExceptionTest {

    @Test
    public void testExceptionMessageAndFieldName() {

        final String expectedFieldName = "email";
        final String expectedMessage = "Invalid input for field";

        final InvalidFieldException exception = new InvalidFieldException(expectedFieldName, expectedMessage);

        assertEquals(expectedFieldName, exception.getFieldName(), "Field name should match the expected field name");
        assertEquals(expectedMessage, exception.getMessage(), "Exception message should match the expected message");
    }
}
