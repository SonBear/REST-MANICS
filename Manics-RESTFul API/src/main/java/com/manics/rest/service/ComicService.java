package com.manics.rest.service;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.Comic;
import com.manics.rest.model.core.Category;
import com.manics.rest.model.core.Chapter;
import com.manics.rest.model.core.Page;
import com.manics.rest.repository.ComicRepository;
import com.manics.rest.service.search.StorySearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComicService extends StoryService {

    private final ComicRepository comicRepository;
    private final CategoryService categoryService;
    private final StorySearchService searchService;
    private final ChapterService chapterService;
    private final PageService pageService;

    @Autowired
    public ComicService(ComicRepository comicRepository, CategoryService categoryService,
            StorySearchService searchService, ChapterService chapterService, PageService pageService) {
        this.comicRepository = comicRepository;
        this.categoryService = categoryService;
        this.searchService = searchService;
        this.chapterService = chapterService;
        this.pageService = pageService;
    }

    public List<Comic> getComics() {
        List<Comic> comics = new ArrayList<>();
        comicRepository.findAll().iterator().forEachRemaining(comics::add);
        return comics;
    }

    public Comic getComicById(Integer id) {
        return comicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("No se encontrĂ³ el comic con el id: %d", id)));
    }

    public Comic createComic(Integer categoryId, Comic comic) {
        Category category = categoryService.getCategory(categoryId);
        comic.setCategory(category);
        comicRepository.save(comic);
        searchService.saveStorySearch(comic);
        return comic;
    }

    public Comic updateComic(Integer comicId, Integer categoryId, Comic newComic) {
        Comic comic = getComicById(comicId);
        Category category = categoryService.getCategory(categoryId);

        comic.updateStory(category, newComic);
        comicRepository.save(comic);
        searchService.updateStorySearch(comic.getId(), comic);
        return comic;
    }

    public Comic deleteComic(Integer comicId) {
        Comic comic = getComicById(comicId);
        comicRepository.delete(comic);
        searchService.deleteStorySearch(comicId);
        return comic;
    }

    public List<Comic> findAllByName(String name) {
        return comicRepository.findByNameLike(name);
    }

    // F

    public List<Chapter> getAllChapters(Integer comicId) {
        Comic comic = getComicById(comicId);
        return comic.getChapters();
    }

    public List<Page> getAllPagesOfChapterComic(Integer comicId, Integer chapterId) {
        Chapter chapter = getChapterOfComic(comicId, chapterId);
        return chapter.getPages();
    }

    public Chapter getComicChapter(Integer comicId, Integer chapterId) {
        Chapter chapter = getChapterOfComic(comicId, chapterId);
        return chapter;
    }

    public Page getPageOfComicChapter(Integer comicId, Integer chapterId, Integer pageId) {
        Page page = getPageOfChapterComic(comicId, chapterId, pageId);
        return page;
    }

    public Chapter createChapterOfComic(Integer comicId, Chapter chapter) {
        return chapterService.createChapter(comicId, chapter);
    }

    public Page createPageOfComicChapter(Integer comicId, Integer chapterId, Page page) {
        Chapter chapter = getChapterOfComic(comicId, chapterId);
        Page newPage = pageService.createPage(chapter.getChapterId(), page);
        searchService.savePageOnStory(comicId, newPage);
        return newPage;
    }

    public Chapter updateChapterOfComic(Integer comicId, Integer chapterId, Chapter newChapter) {
        Chapter chapter = getChapterOfComic(comicId, chapterId);
        return chapterService.updateChapter(chapter.getChapterId(), newChapter);
    }

    public Page updatePageOfChapterComic(Integer comicId, Integer chapterId, Integer pageId, Page newPage) {
        Page page = getPageOfChapterComic(comicId, chapterId, pageId);
        searchService.updatePageOnStory(comicId, page);
        return pageService.updatePage(page.getPageId(), newPage);
    }

    /**
     * Delete no estĂ¡n funcionando.....
     */
    public Chapter deleteChapterOfComic(Integer comicId, Integer chapterId) {
        Chapter chapter = getChapterOfComic(comicId, chapterId);
        chapterService.deleteChapterById(chapterId);
        searchService.deleteAllPagesOfChapter(chapter, comicId); // Esto si funciona
        return chapter;
    }

    public Page deletePageOfChapterComic(Integer comicId, Integer chapterId, Integer pageId) {
        Page page = getPageOfChapterComic(comicId, chapterId, pageId);
        pageService.deletePageById(page.getPageId());
        searchService.deletePageOnStory(comicId, page); // Esto si funciona
        return page;
    }
    // ---------------------------------------

    private boolean checkComicContainsChapter(Comic comic, Chapter chapter) {
        return comic.getChapters().contains(chapter);
    }

    private boolean checkChapterContainsPage(Chapter chapter, Page page) {
        return chapter.getPages().contains(page);
    }

    private Chapter getChapterOfComic(Integer comicId, Integer chapterId) {
        Comic comic = getComicById(comicId);
        Chapter chapter = chapterService.getChapterById(chapterId);
        if (!checkComicContainsChapter(comic, chapter)) {
            throw new NotFoundException("Ese comic no contiene el capitulo con el id: " + chapterId);
        }
        return chapter;
    }

    private Page getPageOfChapterComic(Integer comicId, Integer chapterId, Integer pageId) {
        Chapter chapter = getChapterOfComic(comicId, chapterId);
        Page page = pageService.getPageById(pageId);
        if (!checkChapterContainsPage(chapter, page)) {
            throw new NotFoundException(
                    "El capitulo con el id: " + chapterId + " no contiene la pagina con el id: " + chapterId);
        }
        return page;
    }

}
