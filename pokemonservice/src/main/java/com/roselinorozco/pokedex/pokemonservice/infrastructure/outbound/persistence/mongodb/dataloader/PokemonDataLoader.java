package com.roselinorozco.pokedex.pokemonservice.infrastructure.outbound.persistence.mongodb.dataloader;

import com.roselinorozco.pokedex.pokemonservice.infrastructure.outbound.persistence.mongodb.entity.PokemonEntity;
import com.roselinorozco.pokedex.pokemonservice.infrastructure.outbound.persistence.mongodb.repository.PokemonRepositoryMongoDB;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Roselin Orozco
 */
@Component
public class PokemonDataLoader {

    private final PokemonRepositoryMongoDB pokemonRepositoryMongoDB;

    public PokemonDataLoader(PokemonRepositoryMongoDB pokemonRepositoryMongoDB) {
        this.pokemonRepositoryMongoDB = pokemonRepositoryMongoDB;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadPokemonData() {
        if (this.pokemonRepositoryMongoDB.count() == 0) {
            this.pokemonRepositoryMongoDB.saveAll(List.of(
                    new PokemonEntity("Bulbasaur", "Grass/Poison", false, "Overgrow, Tackle", 69, 7, 5, true, "admin@example.com"),
                    new PokemonEntity("Ivysaur", "Grass/Poison", false, "Overgrow, Quick Attack", 130, 10, 16, true, "admin@example.com"),
                    new PokemonEntity("Venusaur", "Grass/Poison", false, "Overgrow, Solar Beam, Tackle", 1000, 20, 32, true, "admin@example.com"),
                    new PokemonEntity("Charmander", "Fire", false, "Blaze, Ember", 85, 6, 5, true, "admin@example.com"),
                    new PokemonEntity("Charmeleon", "Fire", false, "Blaze, Flamethrower", 190, 11, 16, true, "admin@example.com"),
                    new PokemonEntity("Charizard", "Fire/Flying", false, "Blaze, Fire Blast", 905, 17, 36, true, "admin@example.com"),
                    new PokemonEntity("Mewtwo", "Psychic", true, "Pressure, Psychic, Shadow Ball", 1220, 20, 70, true, "admin@example.com"),
                    new PokemonEntity("Mew", "Psychic", true, "Synchronize, Teleport, Psychic", 40, 4, 100, true, "admin@example.com")
            ));
            System.out.println("Initial Pokemon data loaded.");
        }
    }
}
