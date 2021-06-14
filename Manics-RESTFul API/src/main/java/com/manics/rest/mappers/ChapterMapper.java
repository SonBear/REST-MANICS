package com.manics.rest.mappers;

import java.util.Objects;

import com.manics.rest.model.core.Chapter;
import com.manics.rest.rest.request.ChapterRequest;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class ChapterMapper {

    @AfterMapping
    protected void updateBidirectionalRelationships(@MappingTarget Chapter chapter){
        if(!Objects.isNull(chapter.getPages()))
            chapter.getPages().forEach((page)-> page.setChapter(chapter));;
    }

    public abstract Chapter chapterRquestToChapter(ChapterRequest request);
}
