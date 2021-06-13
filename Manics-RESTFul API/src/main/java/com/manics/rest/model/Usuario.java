package com.manics.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(unique=true)
    private String usuario;

    @Column
    @JsonIgnore
    private String password;

    @Column(unique=true)
    private String email;

    @Column
    @JsonIgnore
    private String token;

    public Usuario() {

    }

    public Usuario(String usuario, String password, String email, String token) {
        this.usuario = usuario;
        this.password = password;
        this.email = email;
        this.token = token;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Usuario [email=" + email + ", password=" + password + ", token=" + token + ", user_id=" + user_id
                + ", usuario=" + usuario + "]";
    }


}