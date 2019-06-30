package com.carlos.invoice.server.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtAuthFilter implements Filter {

    @Value("${security.jwt.token.header}")
    private String authenticationHeader;
    @Value("${security.jwt.token.prefix}")
    private String authenticationPrefix;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String authorization = request.getHeader(authenticationHeader);

        if (authorization != null && authorization.contains(authenticationPrefix)) {
            JwtAuthToken token = new JwtAuthToken(authorization.replace(authenticationPrefix, "").trim());
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
