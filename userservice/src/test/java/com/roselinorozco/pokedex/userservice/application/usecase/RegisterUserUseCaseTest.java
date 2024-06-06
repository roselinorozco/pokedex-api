package com.roselinorozco.pokedex.userservice.application.usecase;

import com.roselinorozco.pokedex.userservice.application.validation.AuthValidationService;
import com.roselinorozco.pokedex.userservice.domain.exception.EmailIsAlreadyRegisteredException;
import com.roselinorozco.pokedex.userservice.domain.exception.InvalidFieldException;
import com.roselinorozco.pokedex.userservice.domain.exception.InvalidPasswordException;
import com.roselinorozco.pokedex.userservice.domain.model.User;
import com.roselinorozco.pokedex.userservice.domain.port.out.UserRepositoryOutputPort;
import com.roselinorozco.pokedex.userservice.domain.port.service.PasswordManagerServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RegisterUserUseCaseTest {

    @Mock
    private UserRepositoryOutputPort userRepositoryOutputPort;
    @Mock
    private PasswordManagerServicePort passwordManagerServicePort;
    @Mock
    private AuthValidationService authValidationService;

    @InjectMocks
    private RegisterUserUseCase registerUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteSuccessfulRegistration() {

        final User newUser = new User("user@example.com", "password123");
        final User expectedUser = new User("user@example.com", "hashedPassword");

        when(userRepositoryOutputPort.findByEmail(newUser.getEmail())).thenReturn(Optional.empty());
        when(passwordManagerServicePort.hashPassword(newUser.getPassword())).thenReturn("hashedPassword");
        when(userRepositoryOutputPort.create(any(User.class))).thenReturn(expectedUser);

        final User registeredUser = registerUserUseCase.execute(newUser);

        assertNotNull(registeredUser);
        assertEquals("hashedPassword", registeredUser.getPassword(), "Password should be hashed");

        verify(authValidationService).validate(newUser.getEmail(), newUser.getPassword());
    }

    @Test
    public void testExecuteThrowEmailAlreadyRegisteredException() {

        final User newUser = new User("user@example.com", "password123");
        when(userRepositoryOutputPort.findByEmail(newUser.getEmail())).thenReturn(Optional.of(new User()));

        assertThrows(EmailIsAlreadyRegisteredException.class, () -> {
            registerUserUseCase.execute(newUser);
        });
    }

    @Test
    public void testExecuteThrowInvalidFieldException() {

        final User newUser = new User("user@example", "abc123");
        doThrow(new InvalidFieldException("email", "The provided email address is invalid."))
                .when(authValidationService).validate(newUser.getEmail(), newUser.getPassword());

        assertThrows(InvalidFieldException.class, () -> {
            registerUserUseCase.execute(newUser);
        });
    }

    @Test
    public void testExecuteThrowInvalidPasswordException() {

        final User newUser = new User("user@example", "");
        doThrow(new InvalidPasswordException("Password must be at least 10 characters long and include one lowercase letter, one uppercase letter and one special character !, @, #, ? or ]."))
                .when(authValidationService).validate(newUser.getEmail(), newUser.getPassword());

        assertThrows(InvalidPasswordException.class, () -> {
            registerUserUseCase.execute(newUser);
        });
    }
}
