package com.roselinorozco.pokedex.pokemonservice.application.usecase;

import com.roselinorozco.pokedex.pokemonservice.domain.port.out.RandomNumberOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * @author Roselin Orozco
 */
public class GetRandomNumberUseCaseTest {

    @Mock
    RandomNumberOutputPort randomNumberOutputPort;

    @InjectMocks
    GetRandomNumberUseCase getRandomNumberUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteReturnsRandomNumber() {

        when(randomNumberOutputPort.getRandomNumber()).thenReturn(42);

        final int result = getRandomNumberUseCase.execute();

        verify(randomNumberOutputPort).getRandomNumber();

        assertEquals(42, result);
    }
}
