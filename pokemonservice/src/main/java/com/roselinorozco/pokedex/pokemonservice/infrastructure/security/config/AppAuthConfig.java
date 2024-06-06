package com.roselinorozco.pokedex.pokemonservice.infrastructure.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Roselin Orozco
 */
@Configuration
public class AppAuthConfig {

    @Value("${app.auth.domain-issuer}")
    private String domainIssuer;

    @Value("${app.auth.audience}")
    private String audience;

    public String getDomainIssuer() {
        return domainIssuer;
    }

    public String getAudience() {
        return audience;
    }
}
