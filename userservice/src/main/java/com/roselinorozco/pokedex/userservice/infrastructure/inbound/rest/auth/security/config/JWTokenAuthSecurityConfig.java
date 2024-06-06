package com.roselinorozco.pokedex.userservice.infrastructure.inbound.rest.auth.security.config;

import com.roselinorozco.pokedex.userservice.domain.port.in.GetTokenForUserInputPort;
import com.roselinorozco.pokedex.userservice.infrastructure.inbound.rest.auth.security.provider.UserAuthenticationProvider;
import com.roselinorozco.pokedex.userservice.infrastructure.inbound.rest.auth.security.filter.JsonEmailPasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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

    final AuthenticationConfiguration authenticationConfiguration;
    final UserAuthenticationProvider userAuthenticationProvider;
    final GetTokenForUserInputPort getTokenForUserInputPort;

    public JWTokenAuthSecurityConfig(AuthenticationConfiguration authenticationConfiguration,
                                     UserAuthenticationProvider userAuthenticationProvider,
                                     GetTokenForUserInputPort getTokenForUserInputPort) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.getTokenForUserInputPort = getTokenForUserInputPort;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        final JsonEmailPasswordAuthenticationFilter jsonEmailPasswordAuthenticationFilter =
                new JsonEmailPasswordAuthenticationFilter(
                        this.authenticationConfiguration.getAuthenticationManager(),
                        this.getTokenForUserInputPort);

        http.cors((cors) -> cors.disable())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers("/api/user/**").permitAll()
                                .requestMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated());

        http.securityContext((securityContext) ->
                securityContext.securityContextRepository(new NullSecurityContextRepository()));

        http.addFilterBefore(jsonEmailPasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(this.userAuthenticationProvider);
        return http.build();
    }
}
