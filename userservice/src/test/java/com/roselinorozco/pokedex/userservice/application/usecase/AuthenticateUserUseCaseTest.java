package com.roselinorozco.pokedex.userservice.application.usecase;

import com.roselinorozco.pokedex.userservice.application.validation.AuthValidationService;
import com.roselinorozco.pokedex.userservice.domain.exception.AuthenticateUserException;
import com.roselinorozco.pokedex.userservice.domain.exception.InvalidFieldException;
import com.roselinorozco.pokedex.userservice.domain.model.User;
import com.roselinorozco.pokedex.userservice.domain.port.out.UserRepositoryOutputPort;
import com.roselinorozco.pokedex.userservice.domain.port.service.PasswordManagerServicePort;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthenticateUserUseCaseTest {

    @Mock
    private UserRepositoryOutputPort userRepositoryOutputPort;

    @Mock
    private PasswordManagerServicePort passwordManagerServicePort;

    @Mock
    private AuthValidationService authValidationService;

    @InjectMocks
    private AuthenticateUserUseCase authenticateUserUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSuccessfulAuthentication() throws AuthenticateUserException {

        final String email = "user@example.com";
        final String password = "abc123";
        final User mockUser = new User(email, password);

        when(userRepositoryOutputPort.findByEmail(email)).thenReturn(Optional.of(mockUser));
        when(passwordManagerServicePort.matches(password, mockUser.getPassword())).thenReturn(true);

        final User result = authenticateUserUseCase.execute(email, password);

        assertNotNull(result);
        assertEquals(email, result.getEmail());

        verify(authValidationService).validate(email, password);
    }

    @Test
    void testAuthenticationWithInvalidEmail() {

        final String email = "invalid@example.com";
        final String password = "abc123";
        when(userRepositoryOutputPort.findByEmail(email)).thenReturn(Optional.empty());

        final Exception exception = assertThrows(AuthenticateUserException.class, () -> {
            authenticateUserUseCase.execute(email, password);
        });

        assertEquals("User by email " + email + " not found", exception.getMessage());
    }

    @Test
    void testAuthenticationWithInvalidPassword() {

        final String email = "user@example.com";
        final String password = "wrongPassword";
        final User mockUser = new User(email, "correctPassword");

        when(userRepositoryOutputPort.findByEmail(email)).thenReturn(Optional.of(mockUser));
        when(passwordManagerServicePort.matches(password, mockUser.getPassword())).thenReturn(false);

        final Exception exception = assertThrows(AuthenticateUserException.class, () -> {
            authenticateUserUseCase.execute(email, password);
        });

        assertEquals("Password does not match for email " + email, exception.getMessage());
    }

    @Test
    void testAuthenticationValidationException() {

        final String email = "email@example";
        final String password = "abc123";

        doThrow(new InvalidFieldException("email", "The provided email address is invalid.")).when(authValidationService).validate(email, password);

        final Exception exception = assertThrows(AuthenticateUserException.class, () -> {
            authenticateUserUseCase.execute(email, password);
        });

        assertEquals("The provided email address is invalid.", exception.getMessage());
    }
}
