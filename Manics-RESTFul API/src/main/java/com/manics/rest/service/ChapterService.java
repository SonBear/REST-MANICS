package com.manics.rest.service;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.core.Chapter;
import com.manics.rest.repository.ChapterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterService {
    
    private final ChapterRepository chapterRepository;

    @Autowired
    private ChapterService(ChapterRepository chapterRepository){
        this.chapterRepository = chapterRepository;
    }

    public Chapter getChapterById(Integer id){
        return chapterRepository.findById(id).orElseThrow(
        ()-> new NotFoundException(String.format("El capitulo con el id: %d no existe", id)));
    }
   
}
