package com.manics.rest.service.stories;

import com.manics.rest.exception.BadRequestException;
import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.core.Chapter;
import com.manics.rest.model.core.Story;
import com.manics.rest.repository.ChapterRepository;
import com.manics.rest.service.search.StorySearchService;
import com.manics.rest.service.stories.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterService {

    @Autowired
    private StoryService storyService;

    @Autowired
    private StorySearchService searchService;

    @Autowired
    private ChapterRepository chapterRepository;

    @Deprecated
    public List<Chapter> getChapters() {
        List<Chapter> chapters = new ArrayList<>();
        chapterRepository.findAll().iterator().forEachRemaining(chapters::add);
        return chapters;
    }

    public List<Chapter> getChaptersByStoryId(Integer storyId) {
        return chapterRepository.getChaptersByStory_Id(storyId);
    }

    @Deprecated
    public Chapter getChapter(Integer chapterId) {
        return chapterRepository
                .findById(chapterId)
                .orElseThrow(
                        () -> new NotFoundException(String.format("El capítulo con el id: %d no existe", chapterId))
                );
    }

    public Chapter getChapter(Integer storyId, Integer chapterId) {
        List<Chapter> chapters = getChaptersByStoryId(storyId);

        chapters.stream()
                .filter(chapter -> chapter.getChapterId().equals(chapterId))
                .findAny()
                .orElseThrow(
                        () -> new BadRequestException(String.format("El relato con el id: %s no tiene ningún capítulo con el id: %s", storyId, chapterId))
                );

        return getChapter(chapterId);
    }

    public Chapter createChapter(Integer storyId, Chapter chapter) {
        Story story = storyService.getStoryById(storyId);
        chapter.setStory(story);
        return chapterRepository.save(chapter);
    }

    public Chapter updateChapter(Integer storyId, Integer chapterId, Chapter newChapter) {
        checkIfChapterExists(storyId, chapterId);

        Chapter chapter = getChapter(chapterId);
        chapter.updateChapter(newChapter);

        return chapterRepository.save(chapter);
    }

    public Chapter deleteChapter(Integer storyId, Integer chapterId) {
        Chapter chapter = getChapter(storyId, chapterId);

        searchService.deleteIndexedChapter(storyId, chapter);
        chapterRepository.deleteById(chapter.getChapterId());

        return chapter;
    }

    public void checkIfChapterExists(Integer storyId, Integer chapterId) {
        getChapter(storyId, chapterId);
    }

}
