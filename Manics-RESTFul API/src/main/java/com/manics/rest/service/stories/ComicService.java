package com.manics.rest.service.stories;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.Comic;
import com.manics.rest.model.core.Category;
import com.manics.rest.repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComicService extends StoryService {

    @Autowired
    private ComicRepository comicRepository;

    public List<Comic> getComics() {
        List<Comic> comics = new ArrayList<>();
        comicRepository.findAll().iterator().forEachRemaining(comics::add);
        return comics;
    }

    public Comic getComicById(Integer id) {
        return comicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("No se encontró el comic con el id: %d", id)));
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
