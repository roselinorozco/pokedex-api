package com.roselinorozco.pokedex.userservice.infrastructure.inbound.rest.auth.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.roselinorozco.pokedex.userservice.application.dto.LoginRequest;
import com.roselinorozco.pokedex.userservice.application.dto.LoginResponse;
import com.roselinorozco.pokedex.userservice.domain.exception.GenerateTokenException;
import com.roselinorozco.pokedex.userservice.domain.model.Token;
import com.roselinorozco.pokedex.userservice.domain.port.in.GetTokenForUserInputPort;
import com.roselinorozco.pokedex.userservice.infrastructure.inbound.rest.auth.adapter.exception.model.ApiError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

/**
 * @author Roselin Orozco
 */
public class JsonEmailPasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final String AUTHORIZATION = "Authorization";
    private final String BEARER = "Bearer";
    private final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private final ObjectMapper objectMapper;
    private final GetTokenForUserInputPort getTokenForUserInputPort;
    private final DateTimeFormatter dateTimeFormatter;

    public JsonEmailPasswordAuthenticationFilter(AuthenticationManager authenticationManager,
                                                 GetTokenForUserInputPort getTokenForUserInputPort) {
        super(new AntPathRequestMatcher("/api/auth/login", "POST"));
        this.getTokenForUserInputPort = getTokenForUserInputPort;
        this.setAuthenticationManager(authenticationManager);
        this.objectMapper = new ObjectMapper();
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN).withZone(ZoneId.systemDefault());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;

        try {
            final LoginRequest loginRequest = this.objectMapper.readValue(request.getInputStream(), LoginRequest.class);

            usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword() != null ? loginRequest.getPassword() : "");

        } catch (Exception exception) {
            throw new AuthenticationServiceException("JSON parsing error");
        }

        return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        try {
            final Token token = getTokenForUserInputPort.execute(authResult.getName());
            response.addHeader(AUTHORIZATION, BEARER + " " + token.token());
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            final LoginResponse loginResponse = new LoginResponse(
                    authResult.getName(),
                    token.token(),
                    this.dateTimeFormatter.format(token.expiryTime())
            );

            String jsonResponse = objectMapper.writeValueAsString(loginResponse);
            response.getWriter().write(jsonResponse);
            response.getWriter().flush();
        } catch (Exception e) {
            this.handleAuthenticationException(e, response);
        } finally {
            response.getWriter().flush();
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {

        final ApiError apiError =
                new ApiError(HttpStatus.UNAUTHORIZED,
                        "Bad Credentials",
                        Collections.singletonList(failed.getMessage()));

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String jsonResponse = objectMapper.writeValueAsString(apiError);
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
    }

    private void handleAuthenticationException(final Exception exception, final HttpServletResponse response) throws IOException {
        final ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                Collections.singletonList(this.getErrorMessageForException(exception)));

        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String jsonResponse = objectMapper.writeValueAsString(apiError);
        response.getWriter().write(jsonResponse);
    }

    private String getErrorMessageForException(final Exception exception) {
        if (exception instanceof GenerateTokenException) {
            return exception.getMessage();
        }
        else if (exception instanceof JOSEException) {
            return "Error creating authentication token";
        }
        return "Error processing authentication";
    }
}

