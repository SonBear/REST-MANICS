package com.manics.rest.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.manics.rest.model.Usuario;
import com.manics.rest.model.request.UsuarioRequest;
import com.manics.rest.service.UsuarioService;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class AuthRest {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register (@RequestBody @Valid UsuarioRequest request) throws URISyntaxException {
        Usuario usuario = usuarioService.crearUsuario(request);

        return ResponseEntity
                .created(new URI("/usuarios/" + usuario.getUser_id()))
                .body(usuario);
    }
}
