package com.roselinorozco.pokedex.userservice.infrastructure.inbound.rest.auth.security.provider;

import com.roselinorozco.pokedex.userservice.domain.exception.AuthenticateUserException;
import com.roselinorozco.pokedex.userservice.domain.model.User;
import com.roselinorozco.pokedex.userservice.domain.port.in.AuthenticateUserInputPort;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author Roselin Orozco
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticateUserInputPort authenticateUserInputPort;

    UserAuthenticationProvider(final AuthenticateUserInputPort authenticateUserInputPort) {
        this.authenticateUserInputPort = authenticateUserInputPort;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        try {
            final User user = this.authenticateUserInputPort.execute(
                    authentication.getName(),
                    authentication.getCredentials().toString()
            );

            return new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    null, // Blanked out password after authentication for security reasons.
                    Collections.singletonList(new SimpleGrantedAuthority("USER"))
            );
        } catch(AuthenticateUserException ex) {
            throw new BadCredentialsException(ex.getMessage());
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
