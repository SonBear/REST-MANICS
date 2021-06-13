package com.manics.rest.service;

import java.util.ArrayList;
import java.util.List;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.ComentarioManga;
import com.manics.rest.rest.request.ComentarioMangaRequest;
import com.manics.rest.repository.ComentarioMangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ComentarioMangaService {
    @Autowired
    ComentarioMangaRepository ComentarioMangaRepository;

    //GET
    public List<ComentarioManga> getComentarios(){
        List<ComentarioManga> comentarios = new ArrayList<>();
        ComentarioMangaRepository.findAll().iterator().forEachRemaining(comentarios::add);
        return comentarios;
    }

    //GET BY ID
    public ComentarioManga getComentario(Integer comentario_id) {
        return ComentarioMangaRepository
                .findById(comentario_id)
                .orElseThrow(
                        () -> new NotFoundException(String.format("No se encontró el comentario con el id: %d", comentario_id))
                );
    }

    //POST
    public ComentarioManga crearComentario(ComentarioMangaRequest request) {
        ComentarioManga comentario = new ComentarioManga(request.getUserId(),request.getmangaId(),request.getContenido());
        ComentarioManga result = ComentarioMangaRepository.save(comentario);
        return result;
    }

    //PUT
    public ComentarioManga updateComentario(Integer comentario_id, ComentarioMangaRequest request) {
        ComentarioManga comentario = getComentario(comentario_id);
        comentario.setContenido(request.getContenido());
        ComentarioMangaRepository.save(comentario);
        return comentario;
    }
    //DELETE
    public ComentarioManga deleteComentario(Integer comentario_id) {
        ComentarioManga comentario = getComentario(comentario_id);
        ComentarioMangaRepository.deleteById(comentario_id);
        return comentario;
    }
}
