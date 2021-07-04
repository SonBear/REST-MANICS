package com.manics.rest.rest;

import com.manics.rest.mappers.ChapterMapper;
import com.manics.rest.mappers.PageMapper;
import com.manics.rest.mappers.StoryMapper;
import com.manics.rest.model.Comic;
import com.manics.rest.model.core.Chapter;
import com.manics.rest.model.core.Page;
import com.manics.rest.rest.request.StoryRequest;
import com.manics.rest.rest.request.chapter.ChapterUpdateRequest;
import com.manics.rest.rest.request.page.PageUpdateRequest;
import com.manics.rest.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("comics")
public class ComicRest {

    private final ComicService comicService;
    private final StoryMapper storyMapper;
    private final ChapterMapper chapterMapper;
    private final PageMapper pageMapper;

    @Autowired
    public ComicRest(ComicService comicService, StoryMapper storyMapper, ChapterMapper chapterMapper,
            PageMapper pageMapper) {
        this.comicService = comicService;
        this.storyMapper = storyMapper;
        this.chapterMapper = chapterMapper;
        this.pageMapper = pageMapper;
    }

    @GetMapping
    public ResponseEntity<List<Comic>> getComics() {
        return ResponseEntity.ok().body(comicService.getComics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comic> getComicById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(comicService.getComicById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Comic> createComic(@RequestBody @Valid StoryRequest request) throws URISyntaxException {

        Comic comic = comicService.createComic(request.getCategoryId(), storyMapper.storyRequestToComic(request));

        return ResponseEntity.created(new URI("/comics/" + comic.getId())).body(comic);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Comic> updateComic(@PathVariable(name = "id") Integer comicId,
            @RequestBody @Valid StoryRequest request) {

        Comic comic = comicService.updateComic(comicId, request.getCategoryId(),
                storyMapper.storyRequestToComic(request));

        return ResponseEntity.ok().body(comic);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Comic> deleteComic(@PathVariable(name = "id") Integer comicId) {
        return ResponseEntity.ok().body(comicService.deleteComic(comicId));
    }

    // Fixed paths
    @GetMapping("/{id}/capitulos")
    public ResponseEntity<List<Chapter>> getComicChapters(@PathVariable(name = "id") Integer comicId) {
        return ResponseEntity.ok().body(comicService.getAllChapters(comicId));
    }

    @GetMapping("/{comicId}/capitulos/{chapterId}/paginas")
    public ResponseEntity<List<Page>> getPagesOfComicChapter(@PathVariable(name = "comicId") Integer comicId,
            @PathVariable(name = "chapterId") Integer chapterId) {
        return ResponseEntity.ok().body(comicService.getAllPagesOfChapterComic(comicId, chapterId));
    }

    @GetMapping("/{comicId}/capitulos/{chapterId}")
    public ResponseEntity<Chapter> getChapter(@PathVariable(name = "comicId") Integer comicId,
            @PathVariable(name = "chapterId") Integer chapterId) {
        return ResponseEntity.ok().body(comicService.getComicChapter(comicId, chapterId));
    }

    @GetMapping("/{comicId}/capitulos/{chapterId}/paginas/{pageId}")
    public ResponseEntity<Page> getPageChapter(@PathVariable(name = "comicId") Integer comicId,
            @PathVariable(name = "chapterId") Integer chapterId, @PathVariable(name = "pageId") Integer pageId) {
        return ResponseEntity.ok().body(comicService.getPageOfComicChapter(comicId, chapterId, pageId));
    }

    @PostMapping("/{comicId}/capitulos")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> createChapter(@PathVariable(name = "comicId") Integer comicId,
            @RequestBody @Valid ChapterUpdateRequest request) throws URISyntaxException {
        Chapter chapter = comicService.createChapterOfComic(comicId,
                chapterMapper.chapterUpdateRequestToChapter(request));
        return ResponseEntity.created(new URI("/comics/" + comicId + "/capitulos/" + chapter.getChapterId()))
                .body(chapter);
    }

    @PostMapping("/{comicId}/capitulos/{chapterId}/paginas")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> createPageChapter(@PathVariable(name = "comicId") Integer comicId,
            @PathVariable(name = "chapterId") Integer chapterId, @RequestBody @Valid PageUpdateRequest request)
            throws URISyntaxException {
        Page page = comicService.createPageOfComicChapter(comicId, chapterId,
                pageMapper.pageUpdateRequestToPage(request));
        return ResponseEntity
                .created(new URI("/comics/" + comicId + "/capitulos/" + chapterId + "/" + page.getPageId())).body(page);

    }

    @PutMapping("/{comicId}/capitulos/{chapterId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> updateChapter(@PathVariable(name = "comicId") Integer comicId,
            @PathVariable(name = "chapterId") Integer chapterId, @Valid ChapterUpdateRequest request) {
        return ResponseEntity.ok().body(comicService.updateChapterOfComic(comicId, chapterId,
                chapterMapper.chapterUpdateRequestToChapter(request)));
    }

    @PutMapping("/{comicId}/capitulos/{chapterId}/paginas/{pageId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> updatePageChapter(@PathVariable(name = "comicId") Integer comicId,
            @PathVariable(name = "chapterId") Integer chapterId, @PathVariable(name = "pageId") Integer pageId,
            @RequestBody PageUpdateRequest request) {
        return ResponseEntity.ok().body(comicService.updatePageOfChapterComic(comicId, chapterId, pageId,
                pageMapper.pageUpdateRequestToPage(request)));
    }

    @DeleteMapping("/{comicId}/capitulos/{chapterId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> deleteChapter(@PathVariable(name = "comicId") Integer comicId,
            @PathVariable(name = "chapterId") Integer chapterId) {
        return ResponseEntity.ok().body(comicService.deleteChapterOfComic(comicId, chapterId));
    }

    @DeleteMapping("/{comicId}/capitulos/{chapterId}/paginas/{pageId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> deletePageChapter(@PathVariable(name = "comicId") Integer comicId,
            @PathVariable(name = "chapterId") Integer chapterId, @PathVariable(name = "pageId") Integer pageId) {
        return ResponseEntity.ok().body(comicService.deletePageOfChapterComic(comicId, chapterId, pageId));
    }

}
