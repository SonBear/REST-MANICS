package com.manics.rest.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.manics.rest.model.manga.Manga;
import com.manics.rest.model.request.manga.MangaRequest;
import com.manics.rest.service.MangaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/mangas/{id}")
    public ResponseEntity<Manga> getManga(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.FOUND).body(mangaService.getManga(id));
    }

    @PostMapping("/mangas")
    public ResponseEntity<Manga> createManga(@RequestBody @Valid MangaRequest request) throws URISyntaxException{
        Manga manga = mangaService.createManga(request);
        return ResponseEntity.created(new URI("/mangas/" + manga.getId())).body(manga);
    }

    @PutMapping("/mangas/{id}")
    public ResponseEntity<Manga> updateManga(@PathVariable Integer id, @RequestBody @Valid MangaRequest request){
        return ResponseEntity.ok().body(mangaService.updateManga(id, request));
    }

    @DeleteMapping("/mangas/{id}")
    public ResponseEntity<Manga> deleteManga(@PathVariable Integer id){
        return ResponseEntity.ok().body(mangaService.deleteManga(id));
    }
}
