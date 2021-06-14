package com.manics.rest.rest.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class StoryRequest {
    @NotNull
    private Integer categoryId;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String author;

    @NotNull
    private Integer publicationYear;

    @NotNull
    private Integer availableChapters;

    private List<ChapterRequest> chapters;

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

    public Integer getAvailableChapters() {
        return availableChapters;
    }

    public void setAvailableChapters(Integer availableChapters) {
        this.availableChapters = availableChapters;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<ChapterRequest> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterRequest> chapters) {
        this.chapters = chapters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
