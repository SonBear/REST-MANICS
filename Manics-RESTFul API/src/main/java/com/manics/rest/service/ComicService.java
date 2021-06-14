package com.manics.rest.service;

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

    private final ComicRepository comicRepository;
    private final CategoryService categoryService;

    @Autowired
    private ComicService(ComicRepository comicRepository, CategoryService categoryService) {
        this.comicRepository = comicRepository;
        this.categoryService = categoryService;
    }

    public List<Comic> getComics() {
        List<Comic> comics = new ArrayList<>();
        comicRepository.findAll().iterator().forEachRemaining(comics::add);
        return comics;
    }

    public Comic getComicById(Integer id) {
        return comicRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("No se encontró el comic con el id: %d", id))
        );
    }

    public Comic createComic(Integer categoryId, Comic comic) {
        Category category = categoryService.getCategory(categoryId);
        comic.setCategory(category);
        return comicRepository.save(comic);
    }

    public Comic updateComic(Integer comicId, Integer categoryId, Comic newComic) {
        Comic comic = getComicById(comicId);
        Category category = categoryService.getCategory(categoryId);

        comic.updateStory(category, newComic);

        return comicRepository.save(comic);
    }

    public Comic deleteComic(Integer comicId) {
        Comic comic = getComicById(comicId);

        comicRepository.delete(comic);

        return comic;
    }

}
