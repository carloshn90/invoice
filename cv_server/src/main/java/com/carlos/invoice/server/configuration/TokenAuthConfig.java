package com.carlos.invoice.server.configuration;

import com.carlos.invoice.server.security.JwtAuthFilter;
import com.carlos.invoice.server.security.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class TokenAuthConfig extends WebSecurityConfigurerAdapter {

    private JwtAuthFilter jwtAuthFilter;
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    public TokenAuthConfig(JwtAuthFilter jwtAuthFilter, JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity
                .authorizeRequests()
                .antMatchers("/login/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        httpSecurity
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling();

        httpSecurity
                .cors();

        httpSecurity
                .csrf()
                .disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.jwtAuthenticationProvider);
    }
}
