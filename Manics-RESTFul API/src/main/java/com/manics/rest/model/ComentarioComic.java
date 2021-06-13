package com.manics.rest.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comentarios_comics")
public class ComentarioComic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comentario_id")
    private Integer id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "comic_id")
    private Integer comic_id;

    @Column(name = "contenido")
    private String contenido;

    @Column(name = "fecha_creacion")
    private Date fecha_creacion;

    public ComentarioComic() {

    }

    public ComentarioComic(Integer user_id, Integer comic_id, String contenido) {
        this.user_id = user_id;
        this.comic_id = comic_id;
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

    public Integer getComid_id() {
        return comic_id;
    }

    public void setComid_id(Integer comic_id) {
        this.comic_id = comic_id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    @Override
    public String toString() {
        return "ComentarioManga [comid_id=" + comic_id + ", contenido=" + contenido + ", fecha_creacion="
                + fecha_creacion + ", id=" + id + ", user_id=" + user_id + "]";
    }



    
}
