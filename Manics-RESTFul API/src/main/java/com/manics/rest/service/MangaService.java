package com.manics.rest.service;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.Manga;
import com.manics.rest.model.core.Category;
import com.manics.rest.model.core.Chapter;
import com.manics.rest.model.core.Page;
import com.manics.rest.repository.MangaRepository;
import com.manics.rest.service.search.StorySearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MangaService extends StoryService {

    private final MangaRepository mangaRepository;
    private final CategoryService categoryService;
    private final StorySearchService searchService;
    private final PageService pageService;
    private final ChapterService chapterService;

    @Autowired
    public MangaService(MangaRepository mangaRepository, CategoryService categoryService,
            StorySearchService searchService, PageService pageService, ChapterService chapterService) {
        this.mangaRepository = mangaRepository;
        this.categoryService = categoryService;
        this.searchService = searchService;
        this.pageService = pageService;
        this.chapterService = chapterService;
    }

    public List<Manga> getMangas() {
        List<Manga> mangas = new ArrayList<>();
        mangaRepository.findAll().iterator().forEachRemaining(mangas::add);
        return mangas;
    }

    public Manga getMangaById(Integer id) {
        return mangaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("No encontramos el manga con el id: %d", id)));
    }

    public Manga createManga(Integer categoryId, Manga manga) {
        Category category = categoryService.getCategory(categoryId);
        manga.setCategory(category);
        mangaRepository.save(manga);
        searchService.saveStorySearch(manga);
        return manga;
    }

    public Manga updateManga(Integer mangaId, Integer categoryId, Manga newManga) {
        Manga manga = getMangaById(mangaId);
        Category category = categoryService.getCategory(categoryId);
        manga.updateStory(category, newManga);
        mangaRepository.save(manga);
        searchService.updateStorySearch(manga.getId(), manga);
        return manga;
    }

    public Manga deleteManga(Integer mangaId) {
        Manga manga = getMangaById(mangaId);
        mangaRepository.delete(manga);
        searchService.deleteStorySearch(manga.getId());
        return manga;
    }

    public List<Manga> findAllByName(String name) {
        return mangaRepository.findByNameLike(name);
    }

    public List<Chapter> getAllChapters(Integer mangaId) {
        Manga manga = getMangaById(mangaId);
        return manga.getChapters();
    }

    public List<Page> getAllPagesOfChapterManga(Integer mangaId, Integer chapterId) {
        Chapter chapter = getChapterOfManga(mangaId, chapterId);
        return chapter.getPages();
    }

    public Chapter getMangaChapter(Integer mangaId, Integer chapterId) {
        Chapter chapter = getChapterOfManga(mangaId, chapterId);
        return chapter;
    }

    public Page getPageOfMangaChapter(Integer mangaId, Integer chapterId, Integer pageId) {
        Page page = getPageOfChapterManga(mangaId, chapterId, pageId);
        return page;
    }

    public Chapter createChapterOfManga(Integer mangaId, Chapter chapter) {
        return chapterService.createChapter(mangaId, chapter);
    }

    public Page createPageOfMangaChapter(Integer mangaId, Integer chapterId, Page page) {
        Chapter chapter = getChapterOfManga(mangaId, chapterId);
        Page newPage = pageService.createPage(chapter.getChapterId(), page);
        searchService.savePageOnStory(mangaId, newPage);
        return newPage;
    }

    public Chapter updateChapterOfManga(Integer mangaId, Integer chapterId, Chapter newChapter) {
        Chapter chapter = getChapterOfManga(mangaId, chapterId);
        return chapterService.updateChapter(chapter.getChapterId(), newChapter);
    }

    public Page updatePageOfChapterManga(Integer mangaId, Integer chapterId, Integer pageId, Page newPage) {
        Page page = getPageOfChapterManga(mangaId, chapterId, pageId);
        searchService.updatePageOnStory(mangaId, page);
        return pageService.updatePage(page.getPageId(), newPage);
    }

    /**
     * Delete no están funcionando.....
     */
    public Chapter deleteChapterOfManga(Integer mangaId, Integer chapterId) {
        Chapter chapter = getChapterOfManga(mangaId, chapterId);
        chapterService.deleteChapterById(chapterId);
        searchService.deleteAllPagesOfChapter(chapter, mangaId); // Esto si funciona
        return chapter;
    }

    public Page deletePageOfChapterManga(Integer mangaId, Integer chapterId, Integer pageId) {
        Page page = getPageOfChapterManga(mangaId, chapterId, pageId);
        pageService.deletePageById(page.getPageId());
        searchService.deletePageOnStory(mangaId, page); // Esto si funciona
        return page;
    }
    // ---------------------------------------

    private boolean checkMangaContainsChapter(Manga manga, Chapter chapter) {
        return manga.getChapters().contains(chapter);
    }

    private boolean checkChapterContainsPage(Chapter chapter, Page page) {
        return chapter.getPages().contains(page);
    }

    private Chapter getChapterOfManga(Integer mangaId, Integer chapterId) {
        Manga manga = getMangaById(mangaId);
        Chapter chapter = chapterService.getChapterById(chapterId);
        if (!checkMangaContainsChapter(manga, chapter)) {
            throw new NotFoundException("Ese manga no contiene el capitulo con el id: " + chapterId);
        }
        return chapter;
    }

    private Page getPageOfChapterManga(Integer mangaId, Integer chapterId, Integer pageId) {
        Chapter chapter = getChapterOfManga(mangaId, chapterId);
        Page page = pageService.getPageById(pageId);
        if (!checkChapterContainsPage(chapter, page)) {
            throw new NotFoundException(
                    "El capitulo con el id: " + chapterId + " no contiene la pagina con el id: " + chapterId);
        }
        return page;
    }
}
