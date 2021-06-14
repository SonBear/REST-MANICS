package com.manics.rest.rest;

import com.manics.rest.mappers.StoryMapper;
import com.manics.rest.model.Manga;
import com.manics.rest.rest.request.StoryRequest;
import com.manics.rest.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class MangaRest {

    private final MangaService mangaService;
    private final StoryMapper storyMapper;

    @Autowired
    private MangaRest(MangaService mangaService, StoryMapper storyMapper) {
        this.mangaService = mangaService;
        this.storyMapper = storyMapper;
    }

    @GetMapping("/mangas")
    public ResponseEntity<List<Manga>> getMangas() {
        return ResponseEntity.ok().body(mangaService.getMangas());
    }

    @GetMapping("/mangas/{id}")
    public ResponseEntity<Manga> getMangaById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(mangaService.getMangaById(id));
    }

    @PostMapping("/mangas")
    public ResponseEntity<Manga> createManga(@RequestBody @Valid StoryRequest request) throws URISyntaxException {

        Manga manga = mangaService.createManga(
                request.getCategoryId(),
                storyMapper.storyRequestToManga(request)
        );

        return ResponseEntity.created(new URI("/mangas/" + manga.getStoryId())).body(manga);
    }

    @PutMapping("/mangas/{id}")
    public ResponseEntity<Manga> updateManga(@PathVariable Integer id,
                                             @RequestBody @Valid StoryRequest request) {

        return ResponseEntity.ok().body(mangaService.updateManga(
                id,
                request.getCategoryId(),
                storyMapper.storyRequestToManga(request)
        ));
    }

    @DeleteMapping("/mangas/{id}")
    public ResponseEntity<Manga> deleteManga(@PathVariable(name = "id") Integer mangaId) {
        return ResponseEntity.ok().body(mangaService.deleteManga(mangaId));
    }

}
