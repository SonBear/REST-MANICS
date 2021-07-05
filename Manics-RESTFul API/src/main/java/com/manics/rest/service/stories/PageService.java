package com.manics.rest.service.stories;

import com.manics.rest.exception.BadRequestException;
import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.core.Chapter;
import com.manics.rest.model.core.Page;
import com.manics.rest.repository.PageRepository;
import com.manics.rest.service.search.StorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {

    private final StorySearchService searchService;
    private final ChapterService chapterService;
    private final PageRepository pageRepository;

    @Autowired
    private PageService(StorySearchService searchService,
                        ChapterService chapterService,
                        PageRepository pageRepository) {

        this.searchService = searchService;
        this.chapterService = chapterService;
        this.pageRepository = pageRepository;
    }

    public List<Page> getPages(Integer storyId, Integer chapterId) {
        chapterService.checkIfChapterExists(storyId, chapterId);

        return pageRepository.getPagesByChapter_ChapterId(chapterId);
    }

    public Page getPageById(Integer pageId) {
        return pageRepository.findById(pageId)
                .orElseThrow(
                        () -> new NotFoundException(String.format("La página con el id: %d no existe", pageId))
                );
    }

    public Page getPage(Integer storyId, Integer chapterId, Integer pageId) {
        chapterService.checkIfChapterExists(storyId, chapterId);

        getPages(storyId, chapterId)
                .stream()
                .filter(page -> page.getPageId().equals(pageId))
                .findAny()
                .orElseThrow(
                        () -> new BadRequestException(String.format("El capítulo con el id: %s no tiene ninguna página con el id: %s", chapterId, pageId))
                );

        return getPageById(pageId);
    }


    public Page createPage(Integer storyId, Integer chapterId, Page page) {
        chapterService.checkIfChapterExists(storyId, chapterId);

        Chapter chapter = chapterService.getChapter(storyId, chapterId);
        page.setChapter(chapter);

        Page newPage = pageRepository.save(page);

        searchService.indexPage(storyId, newPage);

        return newPage;
    }

    public Page updatePage(Integer storyId, Integer chapterId, Integer pageId, Page newPage) {
        Page page = getPage(storyId, chapterId, pageId);

        searchService.updateIndexedPage(storyId, page);
        page.updatePage(newPage);

        return pageRepository.save(page);
    }

    public Page deletePage(Integer storyId, Integer chapterId, Integer pageId) {
        Page page = getPage(storyId, chapterId, pageId);

        searchService.deleteIndexedPage(storyId, page);
        pageRepository.deleteById(pageId);

        return page;
    }

}
