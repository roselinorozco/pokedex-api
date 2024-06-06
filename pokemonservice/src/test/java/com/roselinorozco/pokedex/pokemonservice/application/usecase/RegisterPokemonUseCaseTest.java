package com.roselinorozco.pokedex.pokemonservice.application.usecase;

import com.roselinorozco.pokedex.pokemonservice.domain.exception.RegisterNotAllowedException;
import com.roselinorozco.pokedex.pokemonservice.domain.model.Pokemon;
import com.roselinorozco.pokedex.pokemonservice.domain.port.out.PokemonOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Roselin Orozco
 */
public class RegisterPokemonUseCaseTest {

    @Mock
    PokemonOutputPort pokemonOutputPort;

    @InjectMocks
    RegisterPokemonUseCase registerPokemonUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteValidPokemonReturnsSavedPokemon() {

        final Pokemon validPokemon = new Pokemon("1","Pikachu", "Electric", false, "Thunder Shock", 60, 30, 5, true, "ash@example.com");

        when(pokemonOutputPort.create(validPokemon)).thenReturn(validPokemon);

        final Pokemon result = registerPokemonUseCase.execute(validPokemon);

        verify(pokemonOutputPort).create(validPokemon);

        assertEquals(validPokemon, result);
    }

    @Test
    public void testExecuteInvalidPokemonNameThrowsException() {

        final Pokemon invalidPokemon = new Pokemon("1","123", "Electric", false, "Thunder Shock", 60, 30, 5, true, "ash@example.com");

        assertThrows(RegisterNotAllowedException.class, () -> {
            registerPokemonUseCase.execute(invalidPokemon);
        });
    }

    @Test
    public void testExecuteInvalidPokemonTypeThrowsException() {

        final Pokemon invalidPokemon = new Pokemon("1","Pikachu", "123", false, "Thunder Shock", 60, 30, 5, true, "ash@example.com");

        assertThrows(RegisterNotAllowedException.class, () -> {
            registerPokemonUseCase.execute(invalidPokemon);
        });
    }

    @Test
    public void testExecuteInvalidPokemonWeightThrowsException() {

        final Pokemon invalidPokemon = new Pokemon("1","Pikachu", "Electric", false, "Thunder Shock", -10, 30, 5, true, "ash@example.com");

        assertThrows(RegisterNotAllowedException.class, () -> {
            registerPokemonUseCase.execute(invalidPokemon);
        });
    }

    @Test
    public void testExecuteInvalidPokemonHeightThrowsException() {

        final Pokemon invalidPokemon = new Pokemon("1","Pikachu", "Electric", false, "Thunder Shock", 60, -30, 5, true, "ash@example.com");

        assertThrows(RegisterNotAllowedException.class, () -> {
            registerPokemonUseCase.execute(invalidPokemon);
        });
    }

    @Test
    public void testExecuteInvalidPokemonAbilitiesFormatThrowsException() {

        final Pokemon invalidPokemon = new Pokemon("1","Pikachu", "Electric", false, "Thunder Shock, ", 60, 30, 5, true, "ash@example.com");

        assertThrows(RegisterNotAllowedException.class, () -> {
            registerPokemonUseCase.execute(invalidPokemon);
        });
    }

    @Test
    public void testExecuteInvalidPokemonLevelThrowsException() {

        final Pokemon invalidPokemon = new Pokemon("1","Pikachu", "Electric", false, "Thunder Shock", 60, 30, -5, true, "ash@example.com");

        assertThrows(RegisterNotAllowedException.class, () -> {
            registerPokemonUseCase.execute(invalidPokemon);
        });
    }
}
