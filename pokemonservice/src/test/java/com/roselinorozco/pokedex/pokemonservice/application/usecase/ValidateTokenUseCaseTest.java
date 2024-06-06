package com.roselinorozco.pokedex.pokemonservice.application.usecase;

import com.roselinorozco.pokedex.pokemonservice.domain.exception.ValidateTokenException;
import com.roselinorozco.pokedex.pokemonservice.domain.model.TokenValidationResult;
import com.roselinorozco.pokedex.pokemonservice.domain.port.service.TokenServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Roselin Orozco
 */
public class ValidateTokenUseCaseTest {

    @Mock
    TokenServicePort tokenServicePort;

    @InjectMocks
    ValidateTokenUseCase validateTokenUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteValidTokenReturnsValidResult() throws ValidateTokenException {

        final TokenValidationResult validResult = new TokenValidationResult("ash@example.com", "example.com", "api.example.com", "2024/06/24 12:00:00");
        when(tokenServicePort.validateToken(anyString())).thenReturn(validResult);

        final TokenValidationResult result = validateTokenUseCase.execute("valid_token");
        assertEquals(validResult, result);

        verify(tokenServicePort).validateToken("valid_token");
    }

    @Test
    public void testExecuteInvalidTokenThrowsException() throws ValidateTokenException {

        when(tokenServicePort.validateToken(anyString())).thenThrow(new ValidateTokenException("Invalid token"));

        assertThrows(ValidateTokenException.class, () -> {
            validateTokenUseCase.execute("invalid_token");
        });

        verify(tokenServicePort).validateToken("invalid_token");
    }
}
