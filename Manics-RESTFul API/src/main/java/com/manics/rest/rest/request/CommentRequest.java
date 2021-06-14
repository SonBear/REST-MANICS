package com.manics.rest.rest.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CommentRequest {

    @Min(value = 1)
    @NotNull
    private Integer userId;

    @Min(value = 1)
    @NotNull
    private Integer storyId;

    @NotNull
    @NotEmpty
    private String content;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentRequest{" +
                "userId=" + userId +
                ", storyId=" + storyId +
                ", content='" + content + '\'' +
                '}';
    }

}
