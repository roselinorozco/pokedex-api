package com.roselinorozco.pokedex.pokemonservice.infrastructure.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roselinorozco.pokedex.pokemonservice.domain.exception.ValidateTokenException;
import com.roselinorozco.pokedex.pokemonservice.domain.model.TokenValidationResult;
import com.roselinorozco.pokedex.pokemonservice.domain.port.in.ValidateTokenInputPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * @author Roselin Orozco
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String BEARER_TOKEN_PREFIX = "Bearer ";

    private final ValidateTokenInputPort validateTokenInputPort;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(final ValidateTokenInputPort validateTokenInputPort) {
        this.validateTokenInputPort = validateTokenInputPort;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_TOKEN_PREFIX)) {
            final String token = authorizationHeader.substring(BEARER_TOKEN_PREFIX.length());

            try {
                final TokenValidationResult tokenValidationResult = this.validateTokenInputPort.execute(token);

                if (tokenValidationResult != null && tokenValidationResult.subject() != null) {
                    authenticateUser(tokenValidationResult);
                } else {
                    unauthorized(response, "Unauthorized: Invalid or expired token");
                    return;
                }

            } catch (ValidateTokenException e) {
                unauthorized(response, "Authentication failed: " + e.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateUser(TokenValidationResult tokenValidationResult) {
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                tokenValidationResult.subject(), null, Collections.singletonList(new SimpleGrantedAuthority(tokenValidationResult.audience())));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void unauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), Collections.singletonMap("error", message));
    }
}
