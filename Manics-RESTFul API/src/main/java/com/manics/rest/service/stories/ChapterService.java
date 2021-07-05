package com.manics.rest.service.stories;

import com.manics.rest.exception.BadRequestException;
import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.Comic;
import com.manics.rest.model.core.Chapter;
import com.manics.rest.model.core.Story;
import com.manics.rest.repository.ChapterRepository;
import com.manics.rest.service.search.StorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.manics.rest.model.Manga;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterService {

    public static int TYPE_MANGA = 1;
    public static int TYPE_COMIC = 0;

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
        return chapterRepository.findById(chapterId).orElseThrow(
                () -> new NotFoundException(String.format("El capítulo con el id: %d no existe", chapterId)));
    }

    public Chapter getChapter(Integer storyId, Integer chapterId) {
        List<Chapter> chapters = getChaptersByStoryId(storyId);

        chapters.stream().filter(chapter -> chapter.getChapterId().equals(chapterId)).findAny()
                .orElseThrow(() -> new BadRequestException(String
                        .format("El relato con el id: %s no tiene ningún capítulo con el id: %s", storyId, chapterId)));

        return getChapter(chapterId);
    }

    public Chapter createChapter(Integer storyId, Chapter chapter, int storyType) {
        Story story = storyService.getStoryById(storyId);
        if (storyType == TYPE_MANGA) {
            if (story instanceof Manga) {
                chapter.setStory(story);
                chapterRepository.save(chapter);
                return chapter;
            } else {
                throw new NotFoundException("El manga con el id: " + storyId + " No existe");
            }
        } else {
            if (story instanceof Comic) {
                chapter.setStory(story);
                chapterRepository.save(chapter);
                return chapter;
            }
            throw new NotFoundException("El comic con el id: " + storyId + " No existe");
        }
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
