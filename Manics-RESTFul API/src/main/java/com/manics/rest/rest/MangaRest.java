package com.manics.rest.rest;

import com.manics.rest.mappers.MangaMapper;
import com.manics.rest.model.Manga;
import com.manics.rest.rest.request.MangaRequest;
import com.manics.rest.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("mangas")
public class MangaRest {

    private final MangaService mangaService;
    private final MangaMapper mangaMapper;

    @Autowired
    private MangaRest(MangaService mangaService, MangaMapper mangaMapper) {
        this.mangaService = mangaService;
        this.mangaMapper = mangaMapper;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Manga>> getMangas() {
        return ResponseEntity.ok().body(mangaService.getMangas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manga> getMangaById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(mangaService.getMangaById(id));
    }

    @PostMapping
    public ResponseEntity<Manga> createManga(@RequestBody @Valid MangaRequest request) throws URISyntaxException {
        Manga manga = mangaService.createManga(
                request.getCategoryId(),
                mangaMapper.mangaRequestToManga(request)
        );

        return ResponseEntity.created(new URI("/mangas/" + manga.getId())).body(manga);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manga> updateManga(@PathVariable Integer id,
                                             @RequestBody @Valid MangaRequest request) {

        return ResponseEntity.ok().body(mangaService.updateManga(
                id,
                request.getCategoryId(),
                mangaMapper.mangaRequestToManga(request)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Manga> deleteManga(@PathVariable(name = "id") Integer mangaId) {
        return ResponseEntity.ok().body(mangaService.deleteManga(mangaId));
    }

}
