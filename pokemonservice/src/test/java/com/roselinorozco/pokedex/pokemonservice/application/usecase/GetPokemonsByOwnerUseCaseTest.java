package com.roselinorozco.pokedex.pokemonservice.application.usecase;

import com.roselinorozco.pokedex.pokemonservice.application.validation.PageValidationService;
import com.roselinorozco.pokedex.pokemonservice.domain.exception.InvalidPageOrSizeException;
import com.roselinorozco.pokedex.pokemonservice.domain.model.PokemonPage;
import com.roselinorozco.pokedex.pokemonservice.domain.port.out.PokemonOutputPort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

/**
 * @author Roselin Orozco
 */
public class GetPokemonsByOwnerUseCaseTest {

    @Mock
    private PokemonOutputPort pokemonOutputPort;

    @Mock
    private PageValidationService pageValidationService;

    @InjectMocks
    private GetPokemonsByOwnerUseCase getPokemonsByOwnerUseCase;

    public GetPokemonsByOwnerUseCaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteValidPageAndSizeReturnsPokemonPage() {

        final String ownerID = "123";
        final int page = 0;
        final int size = 10;
        final PokemonPage expectedPokemonPage = new PokemonPage(Collections.emptyList(), 0, 0);

        when(pokemonOutputPort.findPokemonsByOwner(ownerID, page, size)).thenReturn(expectedPokemonPage);

        final PokemonPage result = getPokemonsByOwnerUseCase.execute(ownerID, page, size);

        assertEquals(expectedPokemonPage, result);
        verify(pageValidationService).validate(page, size);
        verify(pokemonOutputPort).findPokemonsByOwner(ownerID, page, size);
    }

    @Test
    public void testExecuteInvalidPageThrowsException() {

        final String ownerID = "123";
        final int page = -1;
        final int size = 10;

        doThrow(new InvalidPageOrSizeException("Page number must be non-negative"))
                .when(pageValidationService).validate(page, size);

        assertThrows(InvalidPageOrSizeException.class, () -> {
            getPokemonsByOwnerUseCase.execute(ownerID, page, size);
        });

        verify(pageValidationService).validate(page, size);
        verifyNoInteractions(pokemonOutputPort);
    }

    @Test
    public void testExecuteInvalidSizeThrowsException() {

        final String ownerID = "123";
        final int page = 0;
        final int size = 0;

        doThrow(new InvalidPageOrSizeException("Page size must be greater than 0"))
                .when(pageValidationService).validate(page, size);

        assertThrows(InvalidPageOrSizeException.class, () -> {
            getPokemonsByOwnerUseCase.execute(ownerID, page, size);
        });

        verify(pageValidationService).validate(page, size);
        verifyNoInteractions(pokemonOutputPort);
    }
}
