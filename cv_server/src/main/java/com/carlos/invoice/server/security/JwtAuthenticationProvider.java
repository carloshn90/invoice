package com.carlos.invoice.server.security;

import com.carlos.invoice.server.exception.TokenAuthenticationException;
import com.carlos.invoice.server.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private JwtService jwtService;

    @Autowired
    public JwtAuthenticationProvider(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        log.debug(authentication.toString());
        log.debug("Credentials: " + authentication.getCredentials());

        if (authentication.getCredentials() == null) {
            throw new TokenAuthenticationException("Failed to verify token");
        }

        return jwtService.verifyJwt((JwtAuthToken) authentication);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return JwtAuthToken.class.equals(aClass);
    }
}
