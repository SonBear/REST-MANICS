package com.manics.rest.service.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.manics.rest.model.core.Story;
import com.manics.rest.model.core.ElasticSearch.StorySearch;
import com.manics.rest.service.AnalyzerImageService;
import com.manics.rest.service.StoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private final AnalyzerImageService analyzerImageService;
    private final StorySearchService storySearchService;
    private final StoryService storyService;

    @Autowired
    public SearchService(AnalyzerImageService analyzerImageService, StorySearchService storySearchService,
            StoryService storyService) {
        this.analyzerImageService = analyzerImageService;
        this.storySearchService = storySearchService;
        this.storyService = storyService;
    }

    public List<Story> searchStoryByImage(String urlImage) {
        Double[] vectorRepresentation;
        List<Story> stories = new ArrayList<>();
        try {
            vectorRepresentation = analyzerImageService.analyzeImageFormUrl(urlImage);
            List<StorySearch> storySearchs = storySearchService.scoreByDenseVector(vectorRepresentation);
            stories = storySearchs.stream().map((storySearch) -> storyService.getStoryById(storySearch.getStoryId()))
                    .collect(Collectors.toList());

        } catch (IOException e) {

        }

        return stories;
    }

    public List<Story> searchStoryByName(String name) {
        List<StorySearch> searchs = storySearchService.findAllByName(name);
        List<Story> stories = searchs.stream().map((s) -> storyService.getStoryById(s.getStoryId()))
                .collect(Collectors.toList());
        return stories;
    }
}
