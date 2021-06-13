package com.manics.rest.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ComentarioMangaRequest {
    @Min(value = 1)
    private Integer userId;

    @Min(value = 1)
    private Integer mangaId;

    @NotEmpty
    private String contenido;

    public ComentarioMangaRequest(){

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUsedId(Integer userId) {
        this.userId = userId;
    }

    public Integer getmangaId() {
        return mangaId;
    }

    public void setmangaId(Integer mangaId) {
        this.mangaId = mangaId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "ComentarioMangaRequest [mangaId=" + mangaId + ", contenido="
                + contenido + ", usedId=" + userId + "]";
    }

    
}
