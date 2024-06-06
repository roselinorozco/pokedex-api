package com.roselinorozco.pokedex.pokemonservice.application.usecase;

import com.roselinorozco.pokedex.pokemonservice.domain.exception.InvalidFieldException;
import com.roselinorozco.pokedex.pokemonservice.domain.exception.RegisterNotAllowedException;
import com.roselinorozco.pokedex.pokemonservice.domain.model.Pokemon;
import com.roselinorozco.pokedex.pokemonservice.domain.port.in.RegisterPokemonInputPort;
import com.roselinorozco.pokedex.pokemonservice.domain.port.out.PokemonOutputPort;

import java.util.regex.Pattern;

/**
 * @author Roselin Orozco
 */
public class RegisterPokemonUseCase implements RegisterPokemonInputPort {

    private final PokemonOutputPort pokemonOutputPort;
    private final Pattern LETTERS_PATTERN = Pattern.compile("^[a-zA-Z]+$");

    // This pattern allows for abilities that consist of multiple words separated by spaces, with each ability separated by commas.
    private final Pattern ABILITIES_PATTERN = Pattern.compile("^([a-zA-Z]+( [a-zA-Z]+)?)(, [a-zA-Z]+( [a-zA-Z]+)?)?$");

    public RegisterPokemonUseCase(final PokemonOutputPort pokemonOutputPort) {
        this.pokemonOutputPort = pokemonOutputPort;
    }

    @Override
    public Pokemon execute(final Pokemon pokemon) {

        this.check(pokemon);
        final Pokemon savedPokemon = this.pokemonOutputPort.create(pokemon);
        return savedPokemon;
    }

    private void check(final Pokemon pokemon) {

        this.checkName(pokemon.getName());
        this.checkType(pokemon.getType());
        this.checkWeight(pokemon.getWeight());
        this.checkHeight(pokemon.getHeight());
        this.checkAbilities(pokemon.getAbilities());
        this.checkAllowedLevel(pokemon.getLevel());
    }

    private void checkName(final String name) {
        if (name == null) {
            throw new InvalidFieldException("name", "name cannot be null");
        }

        if (!isValidString(name, LETTERS_PATTERN)) {
            throw new RegisterNotAllowedException("Invalid name. Only letters are allowed.");
        }
    }

    private void checkType(final String type) {

        if (type == null) {
            throw new InvalidFieldException("type", "type cannot be null");
        }

        if (!isValidString(type, LETTERS_PATTERN)) {
            throw new RegisterNotAllowedException("Invalid type. Only letters are allowed.");
        }
    }

    private void checkAbilities(final String abilities) {

        if (abilities == null) {
            throw new InvalidFieldException("abilities", "abilities cannot be null");
        }

        final String[] abilityArray = abilities.split(",");
        int numAbilities = abilityArray.length;

        if (numAbilities < 1 || numAbilities > 4) {
            throw new RegisterNotAllowedException("A Pokémon must have between 1 and 4 abilities.");
        }

        if (!ABILITIES_PATTERN.matcher(abilities).matches()) {
            throw new RegisterNotAllowedException("Invalid abilities format. Each ability should consist of letters and can include multiple words, separated by commas.");
        }
    }

    private void checkWeight(final Integer weight) {
        if (weight == null) {
            throw new InvalidFieldException("weight", "weight field is required");
        }

        if (!isPositiveNumber(weight)) {
            throw new RegisterNotAllowedException("Invalid weight number. Must be a positive integer not exceeding" + Integer.MAX_VALUE + ".");
        }
    }

    private void checkHeight(final Integer height) {

        if (height == null) {
            throw new InvalidFieldException("height", "height cannot be null");
        }

        if (!isPositiveNumber(height)) {
            throw new RegisterNotAllowedException("Invalid height number. Must be a positive integer not exceeding" + Integer.MAX_VALUE + ".");
        }
    }

    private void checkAllowedLevel(final Integer level) {

        if (level == null) {
            throw new InvalidFieldException("level", "level cannot be null");
        }

        if (level < 1 || level > 100) {
            throw new RegisterNotAllowedException("Pokémon level must be between 1 and 100");
        }
    }

    private boolean isValidString(final String input, final Pattern pattern) {
        return pattern.matcher(input).matches();
    }

    private boolean isPositiveNumber(int number) {
        return number > 0 && number <= Integer.MAX_VALUE;
    }
}
