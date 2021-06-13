package com.manics.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

import com.manics.rest.model.Usuario;
import com.manics.rest.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Usuario usuario = userRepo.findByUsuario(username);

        return User.withUsername(usuario.getUsuario()).password(usuario.getPassword())
                .authorities(Collections.emptyList()).accountExpired(false).accountLocked(false)
                .credentialsExpired(false).disabled(false).build();
    }

}
