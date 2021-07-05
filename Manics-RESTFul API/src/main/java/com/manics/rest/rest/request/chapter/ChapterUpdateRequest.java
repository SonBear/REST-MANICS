package com.manics.rest.rest.request.chapter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ChapterUpdateRequest {

    @NotNull
    private Integer chapterNumber;

    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    private String publicationDate;

    @NotNull
    private Integer totalPages;

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

    @Override
    public String toString() {
        return "ChapterUpdateRequest{" + "chapterNumber=" + chapterNumber + ", name='" + name + '\''
                + ", publicationDate='" + publicationDate + '\'' + ", totalPages=" + totalPages;
    }
}
