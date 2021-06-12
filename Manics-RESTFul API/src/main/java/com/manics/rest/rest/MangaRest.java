package com.manics.rest.rest;

import java.util.List;

import com.manics.rest.model.Manga;
import com.manics.rest.service.MangaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MangaRest {
    
    @Autowired
    private MangaService mangaService;

    @PostMapping("/mangas")
    public ResponseEntity<List<Manga>> getMangas() {
        return ResponseEntity.ok().body(mangaService.getMangas());
    }
}
