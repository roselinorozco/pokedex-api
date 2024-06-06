package com.roselinorozco.pokedex.pokemonservice.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Roselin Orozco
 */
public class TokenValidationResultTest {

    @Test
    void testValidationResult() {
        TokenValidationResult result = new TokenValidationResult("user123@example.com", "test.example.com", "api.example.com", "2024-12-31");

        assertEquals("user123@example.com", result.subject());
        assertEquals("test.example.com", result.issuer());
        assertEquals("api.example.com", result.audience());
        assertEquals("2024-12-31", result.expirationDate());
    }

    @Test
    void testEquality() {
        TokenValidationResult result1 = new TokenValidationResult("user123@example.com", "test.example.com", "api.example.com", "2024-12-31");
        TokenValidationResult result2 = new TokenValidationResult("user123@example.com", "test.example.com", "api.example.com", "2024-12-31");

        assertEquals(result1, result2);
    }

    @Test
    void testNonEquality() {
        TokenValidationResult result1 = new TokenValidationResult("user123@example.com", "test.example.com", "api.example.com", "2024-12-31");
        TokenValidationResult result2 = new TokenValidationResult("user456@example.com", "test2.example.com", "api2.example.com", "2025-01-01");

        assertNotEquals(result1, result2);
    }
}
