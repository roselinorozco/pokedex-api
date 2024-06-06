package com.roselinorozco.pokedex.pokemonservice.application.usecase;

import com.roselinorozco.pokedex.pokemonservice.domain.exception.PokemonDoesNotExistsException;
import com.roselinorozco.pokedex.pokemonservice.domain.exception.UpdateNotAllowedException;
import com.roselinorozco.pokedex.pokemonservice.domain.model.Pokemon;
import com.roselinorozco.pokedex.pokemonservice.domain.port.out.PokemonOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Roselin Orozco
 */
public class UpdatePokemonUseCaseTest {

    @Mock
    PokemonOutputPort pokemonOutputPort;

    @InjectMocks
    UpdatePokemonUseCase updatePokemonUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteValidPokemonReturnsUpdatedPokemon() {

        final Pokemon validPokemon = new Pokemon("1", "Pikachu", "Electric", false, "Thunder Shock", 60, 30, 5, true, "ash@example.com");

        when(pokemonOutputPort.findById(validPokemon.getId())).thenReturn(Optional.of(validPokemon));
        when(pokemonOutputPort.findByIdAndOwner(validPokemon.getId(), validPokemon.getOwner())).thenReturn(Optional.of(validPokemon));
        when(pokemonOutputPort.update(validPokemon)).thenReturn(Optional.of(validPokemon));

        final Optional<Pokemon> result = updatePokemonUseCase.execute(validPokemon);

        verify(pokemonOutputPort).findById(validPokemon.getId());
        verify(pokemonOutputPort).findByIdAndOwner(validPokemon.getId(), validPokemon.getOwner());

        verify(pokemonOutputPort).update(validPokemon);

        assertTrue(result.isPresent());
        assertEquals(validPokemon, result.get());
    }

    @Test
    public void testExecuteNonExistentPokemonThrowsException() {

        final Pokemon nonExistentPokemon = new Pokemon("1", "Pikachu", "Electric", false, "Thunder Shock", 60, 30, 5, true, "ash@example.com");

        when(pokemonOutputPort.findById(nonExistentPokemon.getId())).thenReturn(Optional.empty());

        assertThrows(PokemonDoesNotExistsException.class, () -> {
            updatePokemonUseCase.execute(nonExistentPokemon);
        });

        verify(pokemonOutputPort).findById(nonExistentPokemon.getId());
    }

    @Test
    public void testExecutePokemonNotBelongsToOwnerThrowsException() {

        final Pokemon otherOwnerPokemon = new Pokemon("1", "Pikachu", "Electric", false, "Thunder Shock", 60, 30, 5, true, "misty@example.com");

        when(pokemonOutputPort.findById(otherOwnerPokemon.getId())).thenReturn(Optional.of(otherOwnerPokemon));
        when(pokemonOutputPort.findByIdAndOwner(otherOwnerPokemon.getId(), otherOwnerPokemon.getOwner())).thenReturn(Optional.empty());

        assertThrows(UpdateNotAllowedException.class, () -> {
            updatePokemonUseCase.execute(otherOwnerPokemon);
        });

        verify(pokemonOutputPort).findById(otherOwnerPokemon.getId());

        verify(pokemonOutputPort).findByIdAndOwner(otherOwnerPokemon.getId(), otherOwnerPokemon.getOwner());
    }
}
