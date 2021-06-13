package com.manics.rest.rest.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PageRequest {

    @NotNull
    private Integer pageNumber;

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


}
