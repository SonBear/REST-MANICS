package com.manics.rest.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manics.rest.model.Usuario;
import com.manics.rest.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;


@Service
public class CustomLogoutHandler implements LogoutHandler {

    @Autowired
    private UsuarioRepository userRepo;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION);
        Usuario user = userRepo.findByToken(token);
        user.setToken("");
        userRepo.save(user);
    }

}
