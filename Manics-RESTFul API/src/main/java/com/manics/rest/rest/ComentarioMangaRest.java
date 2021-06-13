package com.manics.rest.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import com.manics.rest.model.ComentarioManga;
import com.manics.rest.rest.request.ComentarioMangaRequest;
import com.manics.rest.service.ComentarioMangaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/mangas")
public class ComentarioMangaRest {
    
    @Autowired
    private ComentarioMangaService ComentarioMangaService;

    @GetMapping("/comentarios")
    public ResponseEntity<List<ComentarioManga>> getComentariosComics() {
        return ResponseEntity.ok().body(ComentarioMangaService.getComentarios());
    }

    @GetMapping("/comentarios/{id}")
    public ResponseEntity<ComentarioManga> getProfesor(@PathVariable Integer id) {
        ComentarioManga comentario = ComentarioMangaService.getComentario(id);
        return ResponseEntity.ok().body(comentario);
    }

    @PostMapping("/comentarios")
    public ResponseEntity<ComentarioManga> createComentario(@RequestBody @Valid ComentarioMangaRequest request) throws URISyntaxException{
        ComentarioManga comentario = ComentarioMangaService.crearComentario(request);
        return ResponseEntity.created(new URI("/comentarios/" + comentario.getId())).body(comentario);
    }

    @PutMapping("/comentarios/{id}")
    public ResponseEntity<ComentarioManga> updateComentario(@PathVariable Integer id, @RequestBody ComentarioMangaRequest request) {
        ComentarioManga comentario = ComentarioMangaService.updateComentario(id, request);
        return ResponseEntity.ok(comentario);
    }

    @DeleteMapping("/comentarios/{id}")
    public ResponseEntity<ComentarioManga> deleteComentario(@PathVariable Integer id) {
        ComentarioManga comentario = ComentarioMangaService.deleteComentario(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(comentario);
    }
}
