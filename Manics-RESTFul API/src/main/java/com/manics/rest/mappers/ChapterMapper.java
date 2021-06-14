package com.manics.rest.mappers;

import com.manics.rest.model.core.Chapter;
import com.manics.rest.rest.request.ChapterRequest;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ChapterMapper {
    public abstract Chapter chapterRquestToChapter(ChapterRequest request);
}
