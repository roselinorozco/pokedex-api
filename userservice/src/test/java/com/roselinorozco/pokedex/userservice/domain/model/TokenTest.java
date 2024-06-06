package com.roselinorozco.pokedex.userservice.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.Instant;

public class TokenTest {

    @Test
    public void testToken() {

        final String tokenValue = "abc123";
        final Instant expiry = Instant.now();

        final Token token = new Token(tokenValue, expiry);

        assertEquals(tokenValue, token.token(), "Token value should match the expected value.");
        assertEquals(expiry, token.expiryTime(), "Expiry date time should match the expected time.");
    }

    @Test
    public void testEquality() {

        final Instant expiry = Instant.now();
        final Token token1 = new Token("abc123", expiry);
        final Token token2 = new Token("abc123", expiry);

        assertEquals(token1, token2, "Two tokens with the same token value and expiry date time should be equal.");
    }
}
