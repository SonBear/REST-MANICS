package com.manics.rest.service;

import java.util.ArrayList;
import java.util.List;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.ComentarioComic;
import com.manics.rest.rest.request.ComentarioComicRequest;
import com.manics.rest.repository.ComentarioComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ComentarioComicService {
    @Autowired
    ComentarioComicRepository comentarioComicRepository;

    //GET
    public List<ComentarioComic> getComentarios(){
        List<ComentarioComic> comentarios = new ArrayList<>();
        comentarioComicRepository.findAll().iterator().forEachRemaining(comentarios::add);
        return comentarios;
    }

    //GET BY ID
    public ComentarioComic getComentario(Integer comentario_id) {
        return comentarioComicRepository
                .findById(comentario_id)
                .orElseThrow(
                        () -> new NotFoundException(String.format("No se encontró el comentario con el id: %d", comentario_id))
                );
    }

    //POST
    public ComentarioComic crearComentario(ComentarioComicRequest request) {
        ComentarioComic comentario = new ComentarioComic(request.getUserId(),request.getComicId(),request.getContenido());
        ComentarioComic result = comentarioComicRepository.save(comentario);
        return result;
    }

    //PUT
    public ComentarioComic updateComentario(Integer comentario_id, ComentarioComicRequest request) {
        ComentarioComic comentario = getComentario(comentario_id);
        comentario.setContenido(request.getContenido());
        comentarioComicRepository.save(comentario);
        return comentario;
    }
    //DELETE
    public ComentarioComic deleteComentario(Integer comentario_id) {
        ComentarioComic comentario = getComentario(comentario_id);
        comentarioComicRepository.deleteById(comentario_id);
        return comentario;
    }
}
