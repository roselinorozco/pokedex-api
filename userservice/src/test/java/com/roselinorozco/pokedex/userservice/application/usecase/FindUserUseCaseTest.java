package com.roselinorozco.pokedex.userservice.application.usecase;

import com.roselinorozco.pokedex.userservice.domain.model.User;
import com.roselinorozco.pokedex.userservice.domain.port.out.UserRepositoryOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FindUserUseCaseTest {

    @Mock
    private UserRepositoryOutputPort userRepositoryOutputPort;

    @InjectMocks
    private FindUserUseCase findUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindUserByEmailSuccess() {

        final String email = "user@example.com";
        final User expectedUser = new User();
        expectedUser.setEmail(email);
        when(userRepositoryOutputPort.findByEmail(email)).thenReturn(Optional.of(expectedUser));

        final Optional<User> result = findUserUseCase.execute(email);

        assertTrue(result.isPresent(), "User should be found");
        assertEquals(expectedUser, result.get(), "The found user should match the expected user");
    }

    @Test
    public void testFindUserByEmailNotFound() {

        final String email = "nonexistent@example.com";
        when(userRepositoryOutputPort.findByEmail(email)).thenReturn(Optional.empty());

        final Optional<User> result = findUserUseCase.execute(email);

        assertFalse(result.isPresent(), "No user should be found");
    }
}
