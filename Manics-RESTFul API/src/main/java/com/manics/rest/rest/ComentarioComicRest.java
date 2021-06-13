package com.manics.rest.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import com.manics.rest.model.ComentarioComic;
import com.manics.rest.model.Manga;
import com.manics.rest.model.request.ComentarioComicRequest;
import com.manics.rest.model.request.MangaRequest;
import com.manics.rest.service.ComentarioComicService;
import com.manics.rest.service.MangaService;

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
@RequestMapping("/api")
public class ComentarioComicRest {
    
    @Autowired
    private ComentarioComicService comentarioComicService;

    @GetMapping("/comentarios")
    public ResponseEntity<List<ComentarioComic>> getComentariosComics() {
        return ResponseEntity.ok().body(comentarioComicService.getComentarios());
    }

    @GetMapping("/comentarios/{id}")
    public ResponseEntity<ComentarioComic> getProfesor(@PathVariable Integer id) {
        ComentarioComic comentario = comentarioComicService.getComentario(id);
        return ResponseEntity.ok().body(comentario);
    }

    @PostMapping("/comentarios")
    public ResponseEntity<ComentarioComic> createComentario(@RequestBody @Valid ComentarioComicRequest request) throws URISyntaxException{
        ComentarioComic comentario = comentarioComicService.crearComentario(request);
        return ResponseEntity.created(new URI("/comentarios/" + comentario.getId())).body(comentario);
    }

    @PutMapping("/comentarios/{id}")
    public ResponseEntity<ComentarioComic> putProfesor(@PathVariable Integer id, @RequestBody ComentarioComicRequest request) {
        ComentarioComic comentario = comentarioComicService.updateComentario(id, request);
        return ResponseEntity.ok(comentario);
    }

    @DeleteMapping("/comentarios/{id}")
    public ResponseEntity<ComentarioComic> deleteComentario(@PathVariable Integer id) {
        ComentarioComic comentario = comentarioComicService.deleteComentario(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(comentario);
    }
}
