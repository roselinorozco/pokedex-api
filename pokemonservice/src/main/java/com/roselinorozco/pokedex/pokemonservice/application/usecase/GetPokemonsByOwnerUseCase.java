package com.roselinorozco.pokedex.pokemonservice.application.usecase;

import com.roselinorozco.pokedex.pokemonservice.application.validation.PageValidationService;
import com.roselinorozco.pokedex.pokemonservice.domain.model.PokemonPage;
import com.roselinorozco.pokedex.pokemonservice.domain.port.in.GetPokemonsByOwnerInputPort;
import com.roselinorozco.pokedex.pokemonservice.domain.port.out.PokemonOutputPort;

/**
 * @author Roselin Orozco
 */
public class GetPokemonsByOwnerUseCase implements GetPokemonsByOwnerInputPort {

    private final PokemonOutputPort pokemonOutputPort;
    private final PageValidationService pageValidationService;

    public GetPokemonsByOwnerUseCase(final PokemonOutputPort pokemonOutputPort,
                                     final PageValidationService pageValidationService) {
        this.pokemonOutputPort = pokemonOutputPort;
        this.pageValidationService = pageValidationService;
    }

    @Override
    public PokemonPage execute(final String ownerID, final Integer page, final Integer size) {
        this.check(page, size);
        return this.pokemonOutputPort.findPokemonsByOwner(ownerID, page, size);
    }

    private void check(final Integer page, final Integer size) {
        pageValidationService.validate(page, size);
    }
}
