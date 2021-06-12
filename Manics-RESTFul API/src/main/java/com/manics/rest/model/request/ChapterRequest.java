package com.manics.rest.model.request;

import java.sql.Date;
import java.util.List;

public class ChapterRequest {
    private Integer mangaId;

    private Integer chapterNumber;

    private String name;

    private Date publicationDate;

    private Integer pageTotal;

    private List<PageRequest> pages;

}
