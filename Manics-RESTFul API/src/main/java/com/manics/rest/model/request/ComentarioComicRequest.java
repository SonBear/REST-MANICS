package com.manics.rest.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ComentarioComicRequest {
    @Min(value = 1)
    private Integer userId;

    @Min(value = 1)
    private Integer comicId;

    @NotEmpty
    private String contenido;

    public ComentarioComicRequest(){

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUsedId(Integer userId) {
        this.userId = userId;
    }

    public Integer getComicId() {
        return comicId;
    }

    public void setComicId(Integer comicId) {
        this.comicId = comicId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "ComentarioComicRequest [comicId=" + comicId + ", contenido="
                + contenido + ", usedId=" + userId + "]";
    }

    
}
