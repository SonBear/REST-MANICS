package com.manics.rest.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import com.manics.rest.mappers.StoryMapper;
import com.manics.rest.model.Comic;
import com.manics.rest.rest.request.StoryRequest;
import com.manics.rest.service.ComicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comics")
public class ComicRest {
    
    private final ComicService comicService;
    private final StoryMapper storyMapper;

    @Autowired
    public ComicRest(ComicService comicService, StoryMapper storyMapper){
        this.comicService = comicService;
        this.storyMapper = storyMapper;
    }

    @GetMapping
    public ResponseEntity<List<Comic>> getComics(){
        return ResponseEntity.ok().body(comicService.getComics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comic> getComicById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.FOUND).body(comicService.getComicById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Comic> createComic(@RequestBody @Valid StoryRequest request) throws URISyntaxException{
        Comic comic = comicService.createComic(
            request.getCategoryId(), storyMapper.storyRequestToComic(request));
        return ResponseEntity.created(new URI("/comics/" + comic.getId())).body(comic);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Comic> updateComic(@PathVariable Integer id, @RequestBody @Valid StoryRequest request){
        Comic comic = comicService.updateComic(id, request.getCategoryId(), storyMapper.storyRequestToComic(request));
        return ResponseEntity.ok().body(comic);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Comic> deleteComic(@PathVariable Integer id){
        return ResponseEntity.ok().body(comicService.deleteComic(id));
    }
}
