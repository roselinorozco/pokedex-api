package com.roselinorozco.pokedex.pokemonservice.infrastructure.security.config;

import com.roselinorozco.pokedex.pokemonservice.domain.port.in.ValidateTokenInputPort;
import com.roselinorozco.pokedex.pokemonservice.infrastructure.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.NullSecurityContextRepository;

/**
 * @author Roselin Orozco
 */
@Configuration
@EnableWebSecurity
public class JWTokenAuthSecurityConfig {

    private final ValidateTokenInputPort validateTokenInputPort;

    public JWTokenAuthSecurityConfig(final ValidateTokenInputPort validateTokenInputPort) {
        this.validateTokenInputPort = validateTokenInputPort;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        final JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(validateTokenInputPort);

        http.cors(CorsConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection for stateless APIs
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) ->
                                requests.requestMatchers("/api/service/**").permitAll()
                                        .requestMatchers("/api/public/**").permitAll()
                        .requestMatchers("/api/private/**").permitAll().anyRequest().authenticated());
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.securityContext((securityContext) ->
                securityContext.securityContextRepository(new NullSecurityContextRepository()));

        return http.build();
    }
}
