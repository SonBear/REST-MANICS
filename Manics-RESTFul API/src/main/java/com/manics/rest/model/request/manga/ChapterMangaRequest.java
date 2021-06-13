package com.manics.rest.model.request.manga;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ChapterMangaRequest {

    @NotNull
    private Integer chapterNumber;

    @NotEmpty
    @NotNull
    private String name;
    
    @NotNull
    private Date publicationDate;
    
    @NotNull
    private Integer pageTotal;
    
    @NotNull
    private List<PageMangaRequest> pages;

  
    public Integer getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(Integer chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List<PageMangaRequest> getPages() {
        return pages;
    }

    public void setPages(List<PageMangaRequest> pages) {
        this.pages = pages;
    }

    
}
