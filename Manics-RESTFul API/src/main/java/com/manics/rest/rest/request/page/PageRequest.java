package com.manics.rest.rest.request.page;

import javax.validation.constraints.NotNull;

public class PageRequest extends PageUpdateRequest {

    @NotNull
    private Integer chapterId;

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

}
