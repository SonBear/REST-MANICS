package com.manics.rest.rest.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ChapterRequest {


    @NotNull
    private Integer chapterNumber;

    private Integer storyId;
    
    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    private String publicationDate;

    @NotNull
    private Integer totalPages;

    private List<PageRequest> pages;

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

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<PageRequest> getPages() {
        return pages;
    }

    public void setPages(List<PageRequest> pages) {
        this.pages = pages;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    @Override
    public String toString() {
        return "ChapterRequest [chapterNumber=" + chapterNumber + ", name=" + name + ", pages=" + pages
                + ", publicationDate=" + publicationDate + ", storyId=" + storyId + ", totalPages=" + totalPages + "]";
    }

    

}
