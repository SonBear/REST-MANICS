package com.manics.rest.model.request;

import java.util.List;

public class MangaRequest {

    private Integer categoriaId;

    private Integer publicationYear;

    private Integer availableChapters;

    private List<ChapterRequest> chapters;

}
