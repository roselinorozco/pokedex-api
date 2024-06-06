package com.roselinorozco.pokedex.userservice.domain.model;

import java.time.Instant;

/**
 * @author Roselin Orozco
 */
public record Token(String token, Instant expiryTime) {}
