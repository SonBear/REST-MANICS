package com.manics.rest.service.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.manics.rest.model.core.Page;
import com.manics.rest.model.core.Story;
import com.manics.rest.model.core.ElasticSearch.PageSearch;
import com.manics.rest.service.AnalyzerImageService;
import com.manics.rest.service.PageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private final PageSearchService pageSearchService;
    private final PageService pageService;
    private final AnalyzerImageService analyzerImageService;

    @Autowired
    public SearchService(PageSearchService pageSearchService, PageService pageService,
            AnalyzerImageService analyzerImageService) {
        this.pageSearchService = pageSearchService;
        this.pageService = pageService;
        this.analyzerImageService = analyzerImageService;
    }

    public List<Story> searchStoryByImage(String urlImage) {
        Double[] vectorRepresentation;
        try {
            vectorRepresentation = analyzerImageService.analyzeImageFormUrl(urlImage);
        } catch (IOException e) {
            throw new RuntimeException("Error no formato invalido de url de una imagen");
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

}
