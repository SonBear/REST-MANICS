package com.manics.rest.rest.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PageRequest {

    @NotNull
    private Integer pageNumber;

    private Integer chapterId;

    @NotEmpty
    @NotNull
    private String imageUrl;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    @Override
    public String toString() {
        return "PageRequest [chapterId=" + chapterId + ", imageUrl=" + imageUrl + ", pageNumber=" + pageNumber + "]";
    }

    


}
