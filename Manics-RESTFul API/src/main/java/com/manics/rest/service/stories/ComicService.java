package com.manics.rest.service.stories;

import com.manics.rest.model.Comic;
import com.manics.rest.model.core.Category;
import com.manics.rest.repository.ComicRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ComicService extends StoryService {
  private final ComicRepository comicRepository;

  public List<Comic> getComics() {
    List<Comic> comics = new ArrayList<>();
    comicRepository.findAll().iterator().forEachRemaining(comics::add);
    return comics;
  }

  public Comic getComicById(Integer comicId) {
    return super.getStoryById(comicId, Comic.class);
  }

  public Comic createComic(Integer categoryId, Comic comic) {
    Category category = categoryService.getCategory(categoryId);
    comic.setCategory(category);
    comicRepository.save(comic);
    searchService.saveStorySearch(comic);
    return comic;
  }

  public Comic updateComic(Integer comicId, Integer categoryId, Comic newComic) {
    Comic comic = getComicById(comicId);
    Category category = categoryService.getCategory(categoryId);

    comic.updateStory(category, newComic);
    comicRepository.save(comic);
    searchService.updateStorySearch(comic.getId(), comic);
    return comic;
  }

  public Comic deleteComic(Integer comicId) {
    Comic comic = getComicById(comicId);
    comicRepository.delete(comic);
    searchService.deleteStorySearch(comicId);
    return comic;
  }

}
