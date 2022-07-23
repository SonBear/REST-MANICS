package com.manics.rest.mappers;

import com.manics.rest.model.Comic;
import com.manics.rest.model.Manga;
import com.manics.rest.model.core.Story;
import com.manics.rest.rest.request.StoryRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Objects;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
public abstract class StoryMapper {

  @AfterMapping
  protected void updateBidirectionalRelationships(@MappingTarget Story story) {
    if (!isNull(story.getChapters())) {
      story.getChapters().forEach(chapter -> {
        if (!isNull(chapter.getPages())) {
          chapter.getPages().forEach(page -> page.setChapter(chapter));
          chapter.setStory(story);
        }
      });
    }
  }

  public abstract Manga storyRequestToManga(StoryRequest storyRequest);

  public abstract Comic storyRequestToComic(StoryRequest storyRequest);

}
