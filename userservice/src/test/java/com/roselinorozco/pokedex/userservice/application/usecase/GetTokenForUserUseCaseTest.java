package com.roselinorozco.pokedex.userservice.application.usecase;

import com.roselinorozco.pokedex.userservice.domain.exception.GenerateTokenException;
import com.roselinorozco.pokedex.userservice.domain.model.Token;
import com.roselinorozco.pokedex.userservice.domain.port.service.TokenServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;

public class GetTokenForUserUseCaseTest {

    @Mock
    private TokenServicePort tokenServicePort;

    @InjectMocks
    private GetTokenForUserUseCase getTokenForUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteReturnsToken() throws GenerateTokenException {

        final String email = "user@example.com";
        final Token expectedToken = new Token("12345", java.time.Instant.now());
        when(tokenServicePort.generateTokenForUserId(email)).thenReturn(expectedToken);

        final Token result = getTokenForUserUseCase.execute(email);

        assertNotNull(result, "Token should not be null");
        assertEquals(expectedToken, result, "The token returned should match the expected token");
    }

    @Test
    public void testExecuteThrowsGenerateTokenException() throws GenerateTokenException {

        final String email = "user@example.com";
        when(tokenServicePort.generateTokenForUserId(email)).thenThrow(new GenerateTokenException("Token generation failed"));

        final GenerateTokenException thrown = assertThrows(GenerateTokenException.class, () -> {
            getTokenForUserUseCase.execute(email);
        }, "GenerateTokenException should be thrown");

        assertEquals("Token generation failed", thrown.getMessage(), "Exception message should match the expected message");
    }
}
