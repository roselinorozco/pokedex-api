package com.roselinorozco.pokedex.userservice.infrastructure.service.adapter;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.roselinorozco.pokedex.userservice.domain.exception.GenerateTokenException;
import com.roselinorozco.pokedex.userservice.domain.model.Token;
import com.roselinorozco.pokedex.userservice.domain.port.service.TokenServicePort;
import com.roselinorozco.pokedex.userservice.infrastructure.inbound.rest.auth.security.config.AppAuthConfig;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

/**
 * @author Roselin Orozco
 */
@Service
public class NimbusJWTTokenServicePortAdapter implements TokenServicePort {

    private static String AUDIENCE = "https://api.example.com";

    private final String TOKEN_GENERATOR_SECRET = "POKEDEX_AUTH_SERVICE_TOKEN_GENERATOR_SECRET";
    private final AppAuthConfig appAuthConfig;
    private final Environment environment;

    public NimbusJWTTokenServicePortAdapter(final AppAuthConfig appAuthConfig, final Environment environment) {
        this.appAuthConfig = appAuthConfig;
        this.environment = environment;
    }

    @Override
    public Token generateTokenForUserId(String userId) throws GenerateTokenException {
        try {
            final Instant expirationInstant = Instant.now()
                    .plusSeconds(this.appAuthConfig.getTokenDurationMinutes() * 60);
            final Date expirationDate = Date.from(expirationInstant);

            final JWSSigner signer = new MACSigner(environment.getProperty(TOKEN_GENERATOR_SECRET).getBytes());

            final JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(userId)
                    .audience(this.appAuthConfig.getAudience())
                    .issuer(this.appAuthConfig.getDomainIssuer())
                    .expirationTime(expirationDate)
                    .build();

            final SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);

            return new Token(signedJWT.serialize(), expirationInstant);
        } catch (JOSEException e) {
            throw new GenerateTokenException("Token generation failed due to internal error.");
        }
    }
}
