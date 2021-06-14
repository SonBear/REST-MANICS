package com.manics.rest.service;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.core.Story;
import com.manics.rest.repository.StoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoryService {
    
    private final StoryRepository storyRepository;

    @Autowired
    private StoryService(StoryRepository storyRepository){
        this.storyRepository = storyRepository;
    }

    public Story getStoryById(Integer id){
        return storyRepository.findById(id).orElseThrow(
            ()-> new NotFoundException(String.format("La historia con el id: %d no existe", id))
        );
    }
}
