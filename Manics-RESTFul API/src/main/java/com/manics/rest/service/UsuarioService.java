package com.manics.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.Usuario;
import com.manics.rest.model.request.UsuarioRequest;
import com.manics.rest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UsuarioService {

    private UsuarioRepository UsuarioRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository UsuarioRepository, PasswordEncoder passwordEncoder) {

        this.UsuarioRepository = UsuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //GET
    public List<Usuario> getUsuarios() {
        return UsuarioRepository.findAll();
    }

    //GET BY ID
    public Usuario getUsuario(Integer usuario_id) {
        return UsuarioRepository
                .findById(usuario_id)
                .orElseThrow(
                        () -> new NotFoundException(String.format("No se encontró el usuario con el id: %d", usuario_id))
                );
    }

    //POST
    public Usuario crearUsuario(UsuarioRequest request) {
        String token = UUID.randomUUID().toString();
        Usuario usuario = new Usuario(request.getUsuario(), passwordEncoder.encode(request.getPassword()), request.getEmail(),token);
        UsuarioRepository.save(usuario);
        return usuario;
    }

    //PUT
    public Usuario updateUsuario(Integer usuario_id, UsuarioRequest request) {
        Usuario usuario = getUsuario(usuario_id);
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setUsuario(request.getUsuario());
        UsuarioRepository.save(usuario);
        return usuario;
    }
    //DELETE
    public Usuario deleteUsuario(Integer usuario_id) {
        Usuario usuario = getUsuario(usuario_id);
        UsuarioRepository.deleteById(usuario_id);
        return usuario;
    }

    public Usuario updateTokenUsuario(String username, String token) {
        Usuario usuario = UsuarioRepository.findByUsuario(username);
        usuario.setToken(token);
        return UsuarioRepository.save(usuario);
    }
}
