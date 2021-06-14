package com.manics.rest.service;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.core.Category;
import com.manics.rest.model.core.Story;
import com.manics.rest.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoryService {

    private StoryRepository storyRepository;

    public StoryService() {

    }

    @Autowired
    private StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public Story getStoryById(Integer storyId) {
        return storyRepository
                .findById(storyId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("No encontramos el relato con el id: %d", storyId))
                );
    }

    public boolean isCategoryBeingUse(Category category) {
        return storyRepository.findAllByCategory(category).size() > 0;
    }


}
