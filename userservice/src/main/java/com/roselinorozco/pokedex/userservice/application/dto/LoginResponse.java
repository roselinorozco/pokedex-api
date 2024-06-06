package com.roselinorozco.pokedex.userservice.application.dto;

/**
 * @author Roselin Orozco
 */
public record LoginResponse(String email, String token, String expiryTime) {}
