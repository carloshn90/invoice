package com.carlos.invoice.server.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.carlos.invoice.server.Application.Constants;
import com.carlos.invoice.server.exception.TokenAuthenticationException;
import com.carlos.invoice.server.security.JwtAuthToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Service
@Validated
public class JwtService {

    private Algorithm algorithm;

    @Value("${security.jwt.token.secretKey}")
    private String secretKey;
    @Value("${security.jwt.token.expirationHours}")
    private String expirationHours;

    public JwtAuthToken verifyJwt(@NotNull JwtAuthToken authToken) {

        if (authToken.getCredentials() == null) {
            throw new TokenAuthenticationException("Failed to verify token");
        }

        try {

            this.algorithm = Algorithm.HMAC256(this.secretKey);

            JWTVerifier verifier = JWT
                    .require(this.algorithm)
                    .build();

            DecodedJWT decodedJWT = verifier
                    .verify((String) authToken.getCredentials());

            String user = decodedJWT.getClaim(Constants.JWT.USER_NAME).asString();

            authToken.setName(user);
            authToken.setAuthenticated(true);

            return authToken;
        } catch (JWTVerificationException ex) {
            throw new TokenAuthenticationException("Failed to verify token");
        }
    }

    public String createValidJwtToken() {

        Calendar expirationCalendar = Calendar.getInstance();
        expirationCalendar.add(Calendar.HOUR, Integer.parseInt(this.expirationHours));

        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

            return JWT
                    .create()
                    .withExpiresAt(expirationCalendar.getTime())
                    .withClaim(Constants.JWT.USER_NAME, "TTest")
                    .sign(algorithm);

        } catch (JWTCreationException ex) {
            return "";
        }
    }
}
