package com.manics.rest.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comentarios_mangas")
public class ComentarioManga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comentario_id")
    private Integer id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "manga_id")
    private Integer manga_id;

    @Column(name = "contenido")
    private String contenido;

    @Column(name = "fecha_publicacion")
    private Date fecha_publicacion;

    public ComentarioManga() {

    }

    public ComentarioManga(Integer user_id, Integer manga_id, String contenido) {
        this.user_id = user_id;
        this.manga_id = manga_id;
        this.contenido = contenido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getManga_id() {
        return manga_id;
    }

    public void setManga_id(Integer manga_id) {
        this.manga_id = manga_id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    @Override
    public String toString() {
        return "ComentarioManga [contenido=" + contenido + ", fecha_publicacion=" + fecha_publicacion + ", id=" + id
                + ", manga_id=" + manga_id + ", user_id=" + user_id + "]";
    }




    
}
