package com.roselinorozco.pokedex.pokemonservice.infrastructure.service.adapter;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.roselinorozco.pokedex.pokemonservice.domain.exception.ValidateTokenException;
import com.roselinorozco.pokedex.pokemonservice.domain.model.TokenValidationResult;
import com.roselinorozco.pokedex.pokemonservice.domain.port.service.TokenServicePort;
import com.roselinorozco.pokedex.pokemonservice.infrastructure.security.config.AppAuthConfig;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Roselin Orozco
 */
@Service
public class NimbusJWTTokenServicePortAdapter implements TokenServicePort {

    private final String TOKEN_VALIDATOR_SECRET = "POKEDEX_POKEMON_SERVICE_TOKEN_VALIDATOR_SECRET";
    private final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final DateTimeFormatter dateTimeFormatter;
    private final AppAuthConfig appAuthConfig;
    private final Environment environment;

    public NimbusJWTTokenServicePortAdapter(final AppAuthConfig appAuthConfig, final Environment environment) {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN).withZone(ZoneId.systemDefault());
        this.appAuthConfig = appAuthConfig;
        this.environment = environment;
    }

    public TokenValidationResult validateToken(final String token) throws ValidateTokenException {

        if (token == null || token.isEmpty()) {
            throw new ValidateTokenException("Token is null or empty");
        }

        try {
            final SignedJWT signedJWT = SignedJWT.parse(token);
            final JWSVerifier verifier = new MACVerifier(environment.getProperty(TOKEN_VALIDATOR_SECRET).getBytes());

            if (!signedJWT.verify(verifier)) {
                throw new ValidateTokenException("Token verification failed");
            }

            final JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            final LocalDateTime localNow = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());

            if (localNow.isAfter(LocalDateTime.ofInstant(claims.getExpirationTime().toInstant(), ZoneId.systemDefault()))) {
                throw new ValidateTokenException("Token has expired as of " + this.dateTimeFormatter.format(localNow) + ".");
            }

            if (claims.getIssuer() == null || !claims.getIssuer().equals(appAuthConfig.getDomainIssuer())) {
                throw new ValidateTokenException("Invalid issuer: expected '" + appAuthConfig.getDomainIssuer() + "' but found '" + claims.getIssuer() + "'");
            }

            final List<String> audiences = claims.getAudience();
            if (audiences.isEmpty() || !audiences.contains(appAuthConfig.getAudience())) {
                throw new ValidateTokenException("Invalid audience: expected '" + appAuthConfig.getAudience() + "' but found '" + String.join(", ", audiences) + "'");
            }

            final String expirationDate = dateTimeFormatter.format(LocalDateTime.ofInstant(claims.getExpirationTime().toInstant(), ZoneId.systemDefault()));

            return new TokenValidationResult(
                    claims.getSubject(),
                    claims.getIssuer(),
                    String.join(",", claims.getAudience()),
                    expirationDate
            );
        } catch (ValidateTokenException validateTokenException) {
            throw validateTokenException;
        } catch (JOSEException e) {
            throw new ValidateTokenException("Token processing error: " + e.getMessage());
        } catch (ParseException e) {
            throw new ValidateTokenException("Token parsing error: " + e.getMessage());
        } catch (Exception e) {
            throw new ValidateTokenException("Unexpected error during token validation: " + e.getMessage());
        }
    }
}
