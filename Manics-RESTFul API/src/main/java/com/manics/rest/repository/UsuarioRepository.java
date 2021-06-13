package com.manics.rest.repository;

import com.manics.rest.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Usuario findByUsuario(String usuario);

    Usuario findByToken(String token);
}
