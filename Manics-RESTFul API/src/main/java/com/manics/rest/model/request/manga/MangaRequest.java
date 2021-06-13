package com.manics.rest.model.request.manga;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

public class MangaRequest {

    @NotNull
    @NotEmpty
    private String name;

    @NonNull
    @NotEmpty
    private String author;

    @NotNull
    private Integer publicationYear;

    @NotNull
    private Integer avaiableChapters;

    @NotNull
    private Integer categoriaId;

    @NonNull
    private List<ChapterMangaRequest> Chapters;

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getAvaiableChapters() {
        return avaiableChapters;
    }

    public void setAvaiableChapters(Integer avaiableChapters) {
        this.avaiableChapters = avaiableChapters;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public List<ChapterMangaRequest> getChapters() {
        return Chapters;
    }

    public void setChapters(List<ChapterMangaRequest> chapters) {
        Chapters = chapters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
