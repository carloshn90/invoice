package com.carlos.invoice.server.exception;


import org.springframework.security.core.AuthenticationException;

public class TokenAuthenticationException extends AuthenticationException {

    public TokenAuthenticationException(String message) {
        super(message);
    }
}
