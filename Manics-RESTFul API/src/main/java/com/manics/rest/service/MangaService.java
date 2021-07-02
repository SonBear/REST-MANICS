package com.manics.rest.service;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.Manga;
import com.manics.rest.model.core.Category;
import com.manics.rest.repository.MangaRepository;
import com.manics.rest.service.search.StorySearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MangaService extends StoryService {

    private final MangaRepository mangaRepository;
    private final CategoryService categoryService;
    private final StorySearchService searchService;

    @Autowired
    private MangaService(MangaRepository mangaRepository, CategoryService categoryService,
            StorySearchService searchService) {
        this.mangaRepository = mangaRepository;
        this.categoryService = categoryService;
        this.searchService = searchService;
    }

    public List<Manga> getMangas() {
        List<Manga> mangas = new ArrayList<>();
        mangaRepository.findAll().iterator().forEachRemaining(mangas::add);
        return mangas;
    }

    public Manga getMangaById(Integer id) {
        return mangaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("No encontramos el manga con el id: %d", id)));
    }

    public Manga createManga(Integer categoryId, Manga manga) {
        Category category = categoryService.getCategory(categoryId);
        manga.setCategory(category);
        mangaRepository.save(manga);
        searchService.saveStorySearch(manga);
        return manga;
    }

    public Manga updateManga(Integer mangaId, Integer categoryId, Manga newManga) {
        Manga manga = getMangaById(mangaId);
        Category category = categoryService.getCategory(categoryId);
        manga.updateStory(category, newManga);
        mangaRepository.save(manga);
        searchService.updateStorySearch(manga.getId(), manga);
        return manga;
    }

    public Manga deleteManga(Integer mangaId) {
        Manga manga = getMangaById(mangaId);
        mangaRepository.delete(manga);
        searchService.deleteStorySearch(manga.getId());
        return manga;
    }

    public List<Manga> findAllByName(String name) {
        return mangaRepository.findByNameLike(name);
    }
}
