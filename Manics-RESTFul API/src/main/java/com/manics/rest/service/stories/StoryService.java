package com.manics.rest.service.stories;

import java.util.List;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.auth.User;
import com.manics.rest.model.core.Category;
import com.manics.rest.model.core.Story;
import com.manics.rest.repository.StoryRepository;
import com.manics.rest.service.search.StorySearchService;
import com.manics.rest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoryService {

    @Autowired
    protected StoryRepository storyRepository;

    @Autowired
    protected UserService userService;

    @Autowired
    protected CategoryService categoryService;

    @Autowired
    protected StorySearchService searchService;

    @Autowired
    protected ChapterService chapterService;

    @Autowired
    protected PageService pageService;

    public StoryService() {

    }

    public Story getStoryById(Integer storyId) {
        return storyRepository.findById(storyId).orElseThrow(
                () -> new NotFoundException(String.format("No encontramos el relato con el id: %d", storyId)));
    }

    public Story toggleLike(Integer storyId, String username) {
        Story story = getStoryById(storyId);
        User user = userService.getUserByUsername(username);

        boolean likedByUser = story.getLikedBy().stream().anyMatch(u -> u.getUserId().equals(user.getUserId()));

        if (likedByUser) {
            story.removeLikedBy(user.getUserId());
            user.removeLike(storyId);
        } else {
            story.addLikedBy(user);
            user.addLike(story);
        }

        userService.saveUser(user);
        return storyRepository.save(story);
    }

    public boolean isCategoryBeingUse(Category category) {
        return storyRepository.findAllByCategory(category).size() > 0;
    }

    public List<Story> getStoriesByCategory(Category category, int maxResults) {
        List<Story> stories = storyRepository.findAllByCategory(category);
        int limit = maxResults < stories.size() ? maxResults : stories.size() - 1;
        return stories.subList(0, limit);
    }

}
