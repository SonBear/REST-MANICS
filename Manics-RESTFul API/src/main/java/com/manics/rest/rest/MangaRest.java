package com.manics.rest.rest;

import com.manics.rest.mappers.MangaMapper;
import com.manics.rest.model.Manga;
import com.manics.rest.rest.request.MangaRequest;
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
@RequestMapping("/api")
public class MangaRest {

    private final MangaService mangaService;
    private final MangaMapper mangaMapper;

    @Autowired
    private MangaRest(MangaService mangaService, MangaMapper mangaMapper) {
        this.mangaService = mangaService;
        this.mangaMapper = mangaMapper;
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
    public ResponseEntity<Manga> createManga(@RequestBody @Valid MangaRequest request) throws URISyntaxException {

        Manga manga = mangaService.createManga(
                request.getCategoryId(),
                mangaMapper.mangaRequestToManga(request)
        );

        return ResponseEntity.created(new URI("/mangas/" + manga.getId())).body(manga);
    }

    @PutMapping("/mangas/{id}")
    public ResponseEntity<Manga> updateManga(@PathVariable Integer id,
                                             @RequestBody @Valid MangaRequest request) {

        return ResponseEntity.ok().body(mangaService.updateManga(
                id,
                request.getCategoryId(),
                mangaMapper.mangaRequestToManga(request)
        ));
    }

    @DeleteMapping("/mangas/{id}")
    public ResponseEntity<Manga> deleteManga(@PathVariable(name = "id") Integer mangaId) {
        return ResponseEntity.ok().body(mangaService.deleteManga(mangaId));
    }

}
