package com.roselinorozco.pokedex.pokemonservice.domain.model;

/**
 * @author Roselin Orozco
 */
public record TokenValidationResult(String subject, String issuer, String audience, String expirationDate) {}
