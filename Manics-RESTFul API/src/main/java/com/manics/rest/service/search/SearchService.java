package com.manics.rest.service.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.manics.rest.model.auth.User;
import com.manics.rest.model.core.Category;
import com.manics.rest.model.core.Story;
import com.manics.rest.model.core.elasticsearch.StorySearch;
import com.manics.rest.service.elasticsearch.AnalyzerImageService;
import com.manics.rest.service.stories.CategoryService;
import com.manics.rest.service.stories.StoryService;
import com.manics.rest.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private final AnalyzerImageService analyzerImageService;
    private final StorySearchService storySearchService;
    private final StoryService storyService;
    private final UserService userService;

    @Autowired
    public SearchService(AnalyzerImageService analyzerImageService, StorySearchService storySearchService,
            StoryService storyService, UserService userService, CategoryService categoryService) {
        this.analyzerImageService = analyzerImageService;
        this.storySearchService = storySearchService;
        this.storyService = storyService;
        this.userService = userService;
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

    public List<Story> searchRecommendations(String username) {
        User user = userService.getUserByUsername(username);
        Set<Story> searchs = user.getLikes();

        Map<Category, Integer> ocurrences = new HashMap<>();
        searchs.forEach((s) -> {
            Integer ocurrence = ocurrences.get(s.getCategory()) == null ? 0 : ocurrences.get(s.getCategory());
            ocurrences.put(s.getCategory(), ++ocurrence);
        });

        Category category = ocurrences.entrySet().stream().max(Comparator.comparing((entry) -> entry.getValue()))
                .orElseThrow(() -> new RuntimeException(
                        "Error al encontrar la categoria más likeada: considera darle like a algun manga o comic"))
                .getKey();

        List<Story> recommendations = storyService.getStoriesByCategory(category, 10);
        return recommendations;
    }
}
