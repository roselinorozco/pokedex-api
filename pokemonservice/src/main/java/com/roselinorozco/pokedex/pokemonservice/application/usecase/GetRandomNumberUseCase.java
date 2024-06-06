package com.roselinorozco.pokedex.pokemonservice.application.usecase;

import com.roselinorozco.pokedex.pokemonservice.domain.port.in.GetRandomNumberInputPort;
import com.roselinorozco.pokedex.pokemonservice.domain.port.out.RandomNumberOutputPort;

/**
 * @author Roselin Orozco
 */
public class GetRandomNumberUseCase implements GetRandomNumberInputPort {

    private final RandomNumberOutputPort randomNumberOutputPort;

    public GetRandomNumberUseCase(RandomNumberOutputPort randomNumberOutputPort) {
        this.randomNumberOutputPort = randomNumberOutputPort;
    }

    @Override
    public int execute() {
        return this.randomNumberOutputPort.getRandomNumber();
    }
}
