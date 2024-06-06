package com.roselinorozco.pokedex.userservice.application.validation;

import com.roselinorozco.pokedex.userservice.domain.exception.InvalidFieldException;
import com.roselinorozco.pokedex.userservice.domain.exception.InvalidPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthValidationServiceTest {

    private AuthValidationService authValidationService;

    @BeforeEach
    void setUp() {
        authValidationService = new AuthValidationService();
    }

    @Test
    public void testValidateValidEmailAndPassword() {

        final String validEmail = "example@example.com";
        final String validPassword = "Password!23";

        assertDoesNotThrow(() -> authValidationService.validate(validEmail, validPassword),
                "Validation should pass with valid email and password.");
    }

    @Test
    public void testValidateInvalidEmail() {

        final String invalidEmail = "example";
        final String validPassword = "Password!23";

        final Exception exception = assertThrows(InvalidFieldException.class,
                () -> authValidationService.validate(invalidEmail, validPassword),
                "InvalidFieldException should be thrown for invalid email.");

        assertEquals("The provided email address is invalid.", exception.getMessage());
    }

    @Test
    public void testValidateInvalidPassword() {

        final String validEmail = "example@example.com";
        final String invalidPassword = "pass";

        final InvalidPasswordException exception = assertThrows(InvalidPasswordException.class,
                () -> authValidationService.validate(validEmail, invalidPassword),
                "InvalidPasswordException should be thrown for invalid password.");

        assertEquals("Password must be at least 10 characters long and include one lowercase letter, one uppercase letter and one special character !, @, #, ? or ].", exception.getMessage());
    }

    @Test
    public void testValidateInvalidEmailAndPassword() {

        final String invalidEmail = "example";
        final String invalidPassword = "pass";

        assertThrows(InvalidFieldException.class,
                () -> authValidationService.validate(invalidEmail, invalidPassword),
                "InvalidFieldException should be thrown first for invalid email.");
    }

    @Test
    public void testValidatePasswordsWithSpecialCharacters() {

        final String[] validPasswords = {
                "Password!1",  // Exclamation mark
                "Password@1",  // At symbol
                "Password#1",  // Hash
                "Password?1",  // Question mark
                "Password]1"   // Closing bracket
        };

        for (String password : validPasswords) {
            assertDoesNotThrow(() -> authValidationService.validate("example@example.com", password),
                    "Validation should pass for password: " + password);
        }
    }

    @Test
    public void testValidateInvalidPasswords() {
        assertAll(
                () -> assertThrows(InvalidPasswordException.class, () -> authValidationService.validate("example@example.com", "Passw1"), "InvalidPasswordException should be thrown for invalid password."),
                () -> assertThrows(InvalidPasswordException.class, () -> authValidationService.validate("example@example.com", "P@ss"), "InvalidPasswordException should be thrown for invalid password."),
                () -> assertThrows(InvalidPasswordException.class, () -> authValidationService.validate("example@example.com", "password!23"), "InvalidPasswordException should be thrown for invalid password."),
                () -> assertThrows(InvalidPasswordException.class, () -> authValidationService.validate("example@example.com", "PASSWORD@1"), "InvalidPasswordException should be thrown for invalid password."),
                () -> assertThrows(InvalidPasswordException.class, () -> authValidationService.validate("example@example.com", "Password123"), "InvalidPasswordException should be thrown for invalid password."),
                () -> assertThrows(InvalidPasswordException.class, () -> authValidationService.validate("example@example.com", "Password$123"), "InvalidPasswordException should be thrown for invalid password."),
                () -> assertThrows(InvalidPasswordException.class, () -> authValidationService.validate("example@example.com", "Password^123"), "InvalidPasswordException should be thrown for invalid password."),
                () -> assertThrows(InvalidPasswordException.class, () -> authValidationService.validate("example@example.com", "Password&123"), "InvalidPasswordException should be thrown for invalid password."),
                () -> assertThrows(InvalidPasswordException.class, () -> authValidationService.validate("example@example.com", "Password*123"), "InvalidPasswordException should be thrown for invalid password.")
        );
    }
}
