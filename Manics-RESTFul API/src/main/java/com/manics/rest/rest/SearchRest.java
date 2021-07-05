package com.manics.rest.rest;

import com.manics.rest.model.core.Story;
import com.manics.rest.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("search")
public class SearchRest {
    private final SearchService searchService;

    @Autowired
    public SearchRest(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/catalogo/byUrlImge")
    public ResponseEntity<List<Story>> searchStoriesByUrlImage(@RequestParam(name = "urlImage") String urlImage) {
        return ResponseEntity.ok().body(searchService.searchStoryByImage(urlImage));
    }

    @GetMapping("/catalogo")
    public ResponseEntity<List<Story>> searchStoriesByName(@RequestParam(name = "q") String name) {
        return ResponseEntity.ok().body(searchService.searchStoryByName(name));
    }

    @GetMapping("/catalogo/recomendaciones")
    public ResponseEntity<List<Story>> searchRecommendations(Principal principal) {
        return ResponseEntity.ok().body(searchService.searchRecommendations(principal.getName()));
    }

}
