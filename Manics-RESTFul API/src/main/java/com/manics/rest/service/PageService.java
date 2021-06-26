package com.manics.rest.service;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.core.Chapter;
import com.manics.rest.model.core.Page;
import com.manics.rest.repository.PageRepository;
import com.manics.rest.service.search.PageSearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageService {

    private final PageRepository pageRepository;
    private final PageSearchService pageSearchService;
    private final ChapterService chapterService;

    @Autowired
    private PageService(PageRepository pageRepository, ChapterService chapterService,
            PageSearchService pageSearchService) {
        this.pageRepository = pageRepository;
        this.chapterService = chapterService;
        this.pageSearchService = pageSearchService;
    }

    public List<Page> getPages() {
        List<Page> pages = new ArrayList<>();
        pageRepository.findAll().iterator().forEachRemaining(pages::add);
        return pages;
    }

    public Page getPageById(Integer id) {
        return pageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("La página con el id: %d no existe", id)));
    }

    public Page createPage(Integer chapterId, Page page) {
        Chapter chapter = chapterService.getChapterById(chapterId);
        page.setChapter(chapter);
        pageRepository.save(page);
        pageSearchService.saveSearchPage(page.getPageId(), page.getImageUrl());
        return page;
    }

    public Page updatePage(Integer pageId, Page newPage) {
        Page page = getPageById(pageId);
        page.updatePage(newPage);
        pageSearchService.updatePageSearch(pageId, page.getImageUrl());
        return pageRepository.save(page);
    }

    public Page deletePage(Integer id) {
        Page page = getPageById(id);
        pageSearchService.deletePageSearch(id);
        pageRepository.delete(page);
        return page;
    }

}
