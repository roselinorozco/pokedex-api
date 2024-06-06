package com.roselinorozco.pokedex.userservice.application.validation;

import com.roselinorozco.pokedex.userservice.domain.exception.InvalidFieldException;
import com.roselinorozco.pokedex.userservice.domain.exception.InvalidPasswordException;

import java.util.regex.Pattern;

/**
 * @author Roselin Orozco
 */
public class AuthValidationService {

    public static final String FIELD_EMAIL = "email";

    private final Pattern EMAIL_PATTERN;
    private final Pattern PASSWORD_PATTERN;

    public AuthValidationService() {
        this.EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        this.PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#?\\]]).{10,}$");
    }

    public void validate(final String email, final String password) {
        isValidEmail(email);
        isValidPassword(password);
    }

    private void isValidEmail(final String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidFieldException(FIELD_EMAIL, "The provided email address is invalid.");
        }
    }

    private void isValidPassword(final String password) {
        if (password == null || !PASSWORD_PATTERN.matcher(password).matches()) {
            throw new InvalidPasswordException("Password must be at least 10 characters long and include one lowercase letter, one uppercase letter and one special character !, @, #, ? or ].");
        }
    }
}
