package com.manics.rest.mappers;

import com.manics.rest.model.Manga;
import com.manics.rest.rest.request.MangaRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class MangaMapper {

    /*
     * Lo mejor que pude hacer para que funcionen correctamente las relaciones bidireccionales.
     */
    @AfterMapping
    protected void updateBidirectionalRelationships(@MappingTarget Manga manga) {
        if (!Objects.isNull(manga.getChapters()))
            manga.getChapters().forEach(chapter -> {
                chapter.getPages().forEach(page -> page.setChapter(chapter));
                chapter.setStory(manga);
            });
    }

    public abstract Manga mangaRequestToManga(MangaRequest mangaRequest);

}
