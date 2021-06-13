package com.manics.rest.rest;

import com.manics.rest.model.Manga;
import com.manics.rest.model.request.MangaRequest;
import com.manics.rest.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
    public ResponseEntity<Manga> createManga(@RequestBody @Valid MangaRequest request) throws URISyntaxException {
        Manga manga = mangaService.createManga(request);
        return ResponseEntity.created(new URI("/mangas/" + manga.getId())).body(manga);
    }
}
