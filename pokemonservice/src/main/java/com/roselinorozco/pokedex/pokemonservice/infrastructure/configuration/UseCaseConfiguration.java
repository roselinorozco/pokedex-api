package com.roselinorozco.pokedex.pokemonservice.infrastructure.configuration;

import com.roselinorozco.pokedex.pokemonservice.application.usecase.*;
import com.roselinorozco.pokedex.pokemonservice.application.validation.PageValidationService;
import com.roselinorozco.pokedex.pokemonservice.domain.port.out.PokemonOutputPort;
import com.roselinorozco.pokedex.pokemonservice.domain.port.out.RandomNumberOutputPort;
import com.roselinorozco.pokedex.pokemonservice.domain.port.service.TokenServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Roselin Orozco
 */
@Configuration
public class UseCaseConfiguration {

    @Bean
    DeletePokemonUseCase deletePokemonUseCase(final PokemonOutputPort pokemonOutputPort) {
        return new DeletePokemonUseCase(pokemonOutputPort);
    }

    @Bean
    GetPokemonsByOwnerUseCase getPokemonsByOwnerUseCase(final PokemonOutputPort pokemonOutputPort) {
        return new GetPokemonsByOwnerUseCase(pokemonOutputPort, new PageValidationService());
    }

    @Bean
    GetPublicPokemonsUseCase getPublicPokemonsUseCase(final PokemonOutputPort pokemonOutputPort) {
        return new GetPublicPokemonsUseCase(pokemonOutputPort, new PageValidationService());
    }

    @Bean
    RegisterPokemonUseCase registerPokemonUseCase(final PokemonOutputPort pokemonOutputPort) {
        return new RegisterPokemonUseCase(pokemonOutputPort);
    }

    @Bean
    UpdatePokemonUseCase updatePokemonUseCase(final PokemonOutputPort pokemonOutputPort) {
        return new UpdatePokemonUseCase(pokemonOutputPort);
    }

    @Bean
    ValidateTokenUseCase validateTokenUseCase(final TokenServicePort tokenServicePort) {
        return new ValidateTokenUseCase(tokenServicePort);
    }

    @Bean
    GetRandomNumberUseCase getRandomNumberUseCase(final RandomNumberOutputPort randomNumberOutputPort) {
        return new GetRandomNumberUseCase(randomNumberOutputPort);
    }
}
