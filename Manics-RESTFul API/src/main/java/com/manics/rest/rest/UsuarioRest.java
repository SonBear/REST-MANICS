package com.manics.rest.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import com.manics.rest.model.Usuario;
import com.manics.rest.model.request.UsuarioRequest;
import com.manics.rest.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api")
public class UsuarioRest {
    
    @Autowired
    private UsuarioService UsuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getUsuariosComics() {
        return ResponseEntity.ok().body(UsuarioService.getUsuarios());
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Integer id) {
        Usuario usuario = UsuarioService.getUsuario(id);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> createUsuario(@RequestBody @Valid UsuarioRequest request) throws URISyntaxException{
        Usuario usuario = UsuarioService.crearUsuario(request);
        return ResponseEntity.created(new URI("/usuarios/" + usuario.getUser_id())).body(usuario);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody UsuarioRequest request) {
        Usuario usuario = UsuarioService.updateUsuario(id, request);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable Integer id) {
        Usuario usuario = UsuarioService.deleteUsuario(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuario);
    }
}
