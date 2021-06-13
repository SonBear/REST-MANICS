package com.manics.rest.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.manics.rest.model.request.UsuarioRequest;
import com.manics.rest.service.UsuarioService;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class UsuarioAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;

    public UsuarioAuthenticationFilter(AuthenticationManager authenticationManager,
                                       UsuarioService usuarioService) {

        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {

        try {
            UsuarioRequest authRequest = new ObjectMapper().readValue(
                    request.getInputStream(),
                    UsuarioRequest.class
            );

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authRequest.getUsuario(),
                    authRequest.getPassword()
            );

            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();
        String token = UUID.randomUUID().toString();

        usuarioService.updateTokenUsuario(user.getUsername(), token);

        response.addHeader(
                HttpHeaders.AUTHORIZATION,
                token
        );

    }
}
