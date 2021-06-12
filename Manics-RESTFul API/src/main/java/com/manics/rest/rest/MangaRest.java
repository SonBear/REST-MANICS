package com.manics.rest.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import com.manics.rest.model.Manga;
import com.manics.rest.model.request.MangaRequest;
import com.manics.rest.service.MangaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MangaRest {
    
    @Autowired
    private MangaService mangaService;

    @GetMapping("/mangas")
    public ResponseEntity<List<Manga>> getMangas() {
        return ResponseEntity.ok().body(mangaService.getMangas());
    }

    @PostMapping("/mangas")
    public ResponseEntity<Manga> createManga(@RequestBody @Valid MangaRequest request) throws URISyntaxException{
        Manga manga = mangaService.createManga(request);
        return ResponseEntity.created(new URI("/mangas/" + manga.getId())).body(manga);
    }
}
