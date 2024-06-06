package com.roselinorozco.pokedex.pokemonservice.infrastructure.inbound.rest.randomnumber.adapter.controller;

import com.roselinorozco.pokedex.pokemonservice.domain.port.in.GetRandomNumberInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Roselin Orozco
 */
@RestController
@RequestMapping("/api/service/randomnumber")
public class RandomNumberRestController {

    private final GetRandomNumberInputPort getRandomNumberInputPort;

    public RandomNumberRestController(final GetRandomNumberInputPort getRandomNumberInputPort) {
        this.getRandomNumberInputPort = getRandomNumberInputPort;
    }

    @GetMapping("")
    public ResponseEntity<?> getRandomNumber() {
        return ResponseEntity.ok(this.getRandomNumberInputPort.execute());
    }
}
