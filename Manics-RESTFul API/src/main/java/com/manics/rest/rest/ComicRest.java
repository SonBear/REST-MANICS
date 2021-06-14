package com.manics.rest.rest;

import com.manics.rest.mappers.StoryMapper;
import com.manics.rest.model.Comic;
import com.manics.rest.rest.request.StoryRequest;
import com.manics.rest.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("comics")
public class ComicRest {

    private final ComicService comicService;
    private final StoryMapper storyMapper;

    @Autowired
    private ComicRest(ComicService comicService, StoryMapper storyMapper) {
        this.comicService = comicService;
        this.storyMapper = storyMapper;
    }

    @GetMapping
    public ResponseEntity<List<Comic>> getComics() {
        return ResponseEntity.ok().body(comicService.getComics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comic> getComicById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(comicService.getComicById(id));
    }

    @PostMapping
    public ResponseEntity<Comic> createComic(@RequestBody @Valid StoryRequest request) throws URISyntaxException {
        Comic comic = comicService.createComic(
                request.getCategoryId(), storyMapper.storyRequestToComic(request)
        );

        return ResponseEntity.created(new URI("/comics/" + comic.getId())).body(comic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comic> updateComic(@PathVariable(name = "id") Integer comicId,
                                             @RequestBody @Valid StoryRequest request) {

        Comic comic = comicService.updateComic(
                comicId,
                request.getCategoryId(),
                storyMapper.storyRequestToComic(request)
        );

        return ResponseEntity.ok().body(comic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comic> deleteComic(@PathVariable Integer id) {
        return ResponseEntity.ok().body(comicService.deleteComic(id));
    }

}
