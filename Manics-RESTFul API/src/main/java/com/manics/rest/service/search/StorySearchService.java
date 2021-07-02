package com.manics.rest.service.search;

import java.util.ArrayList;
import java.util.List;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.core.Story;
import com.manics.rest.model.core.ElasticSearch.StorySearch;
import com.manics.rest.repository.elasticsearch.StorySearchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorySearchService {
    private final StorySearchRepository storySearchRepository;

    @Autowired
    public StorySearchService(StorySearchRepository storySearchRepository) {
        this.storySearchRepository = storySearchRepository;
    }

    public List<StorySearch> getStories() {
        List<StorySearch> stories = new ArrayList<>();
        storySearchRepository.findAll().iterator().forEachRemaining(stories::add);
        return stories;
    }

    public StorySearch getStorySearch(Integer id) {
        return storySearchRepository.findById(id).orElseThrow(() -> new NotFoundException("F"));
    }

    public StorySearch saveStorySearch(Story story) {
        StorySearch storySearch = new StorySearch();
        storySearch.setStoryId(story.getId());
        storySearch.setName(story.getName());
        return storySearchRepository.save(storySearch);
    }

    public StorySearch deleteStorySearch(Integer id) {
        StorySearch storySearch = getStorySearch(id);
        storySearchRepository.delete(storySearch);
        return storySearch;
    }

    public StorySearch updateStorySearch(Integer id, Story story) {
        StorySearch storySearch = getStorySearch(id);
        storySearch.setStoryId(story.getId());
        storySearch.setName(story.getName());
        storySearchRepository.save(storySearch);
        return storySearch;
    }

    public List<StorySearch> findAllByName(String name) {
        return storySearchRepository.findByNameLike(name);
    }

}
