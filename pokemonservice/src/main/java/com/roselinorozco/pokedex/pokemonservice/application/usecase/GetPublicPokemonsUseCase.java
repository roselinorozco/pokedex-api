package com.roselinorozco.pokedex.pokemonservice.application.usecase;

import com.roselinorozco.pokedex.pokemonservice.application.validation.PageValidationService;
import com.roselinorozco.pokedex.pokemonservice.domain.model.PokemonPage;
import com.roselinorozco.pokedex.pokemonservice.domain.port.in.GetPublicPokemonsInputPort;
import com.roselinorozco.pokedex.pokemonservice.domain.port.out.PokemonOutputPort;

/**
 * @author Roselin Orozco
 */
public class GetPublicPokemonsUseCase implements GetPublicPokemonsInputPort {

    private final PokemonOutputPort pokemonOutputPort;
    private final PageValidationService pageValidationService;

    public GetPublicPokemonsUseCase(final PokemonOutputPort pokemonOutputPort,
                                    final PageValidationService pageValidationService) {
        this.pokemonOutputPort = pokemonOutputPort;
        this.pageValidationService = pageValidationService;
    }

    @Override
    public PokemonPage execute(final Integer page, final Integer size) {
        this.check(page, size);
        return this.pokemonOutputPort.findPublicPokemons(page.intValue(), size.intValue());
    }

    private void check(final Integer page, final Integer size) {
        pageValidationService.validate(page, size);
    }
}
