package com.roselinorozco.pokedex.pokemonservice.application.usecase;

import com.roselinorozco.pokedex.pokemonservice.domain.exception.DeleteNotAllowedException;
import com.roselinorozco.pokedex.pokemonservice.domain.exception.PokemonBelongToOtherOwnerException;
import com.roselinorozco.pokedex.pokemonservice.domain.exception.PokemonDoesNotExistsException;
import com.roselinorozco.pokedex.pokemonservice.domain.model.Pokemon;
import com.roselinorozco.pokedex.pokemonservice.domain.port.out.PokemonOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Roselin Orozco
 */
public class DeletePokemonUseCaseTest {

    @Mock
    private PokemonOutputPort pokemonOutputPort;

    @InjectMocks
    private DeletePokemonUseCase deletePokemonUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecutePokemonExistsAndBelongsToOwnerSuccess() {

        final String owner = "ash@example.com";
        final String id = "123";
        when(pokemonOutputPort.findById(id)).thenReturn(Optional.of(new Pokemon()));
        when(pokemonOutputPort.findByIdAndOwner(id, owner)).thenReturn(Optional.of(new Pokemon()));
        when(pokemonOutputPort.delete(id)).thenReturn(true);

        final boolean result = deletePokemonUseCase.execute(owner, id);

        assertTrue(result);
        Mockito.verify(pokemonOutputPort).delete(id);
    }

    @Test
    public void testExecutePokemonDoesNotExistExceptionThrown() {

        final String owner = "ash@example.com";
        final String id = "123";
        when(pokemonOutputPort.findById(id)).thenReturn(Optional.empty());

        assertThrows(PokemonDoesNotExistsException.class, () -> {
            deletePokemonUseCase.execute(owner, id);
        });
    }

    @Test
    public void testExecutePokemonBelongsToDifferentOwnerExceptionThrown() {

        final String owner = "ash@example.com";
        final String id = "123";
        when(pokemonOutputPort.findById(id)).thenReturn(Optional.of(new Pokemon()));
        when(pokemonOutputPort.findByIdAndOwner(id, owner)).thenReturn(Optional.empty());

        assertThrows(PokemonBelongToOtherOwnerException.class, () -> {
            deletePokemonUseCase.execute(owner, id);
        });
    }
}
