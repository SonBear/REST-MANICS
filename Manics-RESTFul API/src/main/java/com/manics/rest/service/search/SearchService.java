package com.manics.rest.service.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.manics.rest.model.Comic;
import com.manics.rest.model.Manga;
import com.manics.rest.model.core.Page;
import com.manics.rest.model.core.Story;
import com.manics.rest.model.core.ElasticSearch.PageSearch;
import com.manics.rest.model.core.ElasticSearch.StorySearch;
import com.manics.rest.repository.StoryRepository;
import com.manics.rest.service.AnalyzerImageService;
import com.manics.rest.service.ComicService;
import com.manics.rest.service.MangaService;
import com.manics.rest.service.PageService;
import com.manics.rest.service.StoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private final PageSearchService pageSearchService;
    private final PageService pageService;
    private final AnalyzerImageService analyzerImageService;
    private final MangaService mangaService;
    private final ComicService comicService;
    private final StorySearchService storySearchService;
    private final StoryService storyService;

    @Autowired
    public SearchService(PageSearchService pageSearchService, PageService pageService,
            AnalyzerImageService analyzerImageService, MangaService mangaService, ComicService comicService,
            StorySearchService storySearchService, StoryService storyService) {
        this.pageSearchService = pageSearchService;
        this.pageService = pageService;
        this.analyzerImageService = analyzerImageService;
        this.mangaService = mangaService;
        this.comicService = comicService;
        this.storySearchService = storySearchService;
        this.storyService = storyService;

    }

    public List<Story> searchStoryByImage(String urlImage) {
        Double[] vectorRepresentation;
        try {
            vectorRepresentation = analyzerImageService.analyzeImageFormUrl(urlImage);
        } catch (IOException e) {
            throw new RuntimeException("Error url no valido para una imagen");
        }
        List<PageSearch> similarPages = pageSearchService.scoreByDenseVector(vectorRepresentation);

        List<Page> pages = similarPages.stream().map((pageSearch) -> pageService.getPageById(pageSearch.getPageId()))
                .collect(Collectors.toList());

        List<Story> stories = new ArrayList<>();
        pages.forEach((page) -> {
            Story story = page.getChapter().getStory();
            if (!stories.contains(story)) {
                stories.add(story);
            }
        });

        return stories;
    }

    public List<Manga> searchMangasByName(String name) {
        return mangaService.findAllByName(name);
    }

    public List<Comic> searchComicByName(String name) {
        return comicService.findAllByName(name);
    }

    public List<Story> searchStoryByName(String name) {
        List<StorySearch> searchs = storySearchService.findAllByName(name);
        List<Story> stories = searchs.stream().map((s) -> storyService.getStoryById(s.getStoryId()))
                .collect(Collectors.toList());
        return stories;
    }
}
