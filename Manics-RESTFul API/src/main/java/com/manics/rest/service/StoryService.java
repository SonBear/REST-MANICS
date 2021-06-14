package com.manics.rest.service;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.core.Story;
import com.manics.rest.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoryService {

    @Autowired
    private StoryRepository storyRepository;

    public Story getStoryById(Integer storyId) {
        return storyRepository
                .findById(storyId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("No encontramos el relato con el id: %d", storyId))
                );
    }

    public void checkIfStoryExists(Integer storyId) {
        getStoryById(storyId);
    }
}
