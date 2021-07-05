package com.manics.rest.rest.story;

import com.manics.rest.mappers.ChapterMapper;
import com.manics.rest.mappers.PageMapper;
import com.manics.rest.mappers.StoryMapper;
import com.manics.rest.model.Comic;
import com.manics.rest.model.core.Chapter;
import com.manics.rest.model.core.Page;
import com.manics.rest.model.core.Story;
import com.manics.rest.rest.request.StoryRequest;
import com.manics.rest.rest.request.chapter.ChapterUpdateRequest;
import com.manics.rest.rest.request.page.PageUpdateRequest;
import com.manics.rest.service.stories.ChapterService;
import com.manics.rest.service.stories.ComicService;
import com.manics.rest.service.stories.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("comics")
public class ComicRest {

    private final ComicService comicService;
    private final ChapterService chapterService;
    private final PageService pageService;

    private final StoryMapper storyMapper;
    private final ChapterMapper chapterMapper;
    private final PageMapper pageMapper;

    @Autowired
    public ComicRest(ComicService comicService, ChapterService chapterService, PageService pageService,
            StoryMapper storyMapper, ChapterMapper chapterMapper, PageMapper pageMapper) {

        this.comicService = comicService;
        this.chapterService = chapterService;
        this.pageService = pageService;

        this.storyMapper = storyMapper;
        this.chapterMapper = chapterMapper;
        this.pageMapper = pageMapper;
    }

    @GetMapping
    public ResponseEntity<List<Comic>> getComics() {
        return ResponseEntity.ok().body(comicService.getComics());
    }

    @GetMapping("/{comicId}")
    public ResponseEntity<Comic> getComicById(@PathVariable Integer comicId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(comicService.getComicById(comicId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Comic> createComic(@RequestBody @Valid StoryRequest request) throws URISyntaxException {

        Comic comic = comicService.createComic(request.getCategoryId(), storyMapper.storyRequestToComic(request));

        return ResponseEntity.created(new URI("/comics/" + comic.getId())).body(comic);
    }

    @PutMapping("/{comicId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Comic> updateComic(@PathVariable Integer comicId, @RequestBody @Valid StoryRequest request) {

        Comic comic = comicService.updateComic(comicId, request.getCategoryId(),
                storyMapper.storyRequestToComic(request));

        return ResponseEntity.ok().body(comic);
    }

    @DeleteMapping("/{comicId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Comic> deleteComic(@PathVariable Integer comicId) {
        return ResponseEntity.ok().body(comicService.deleteComic(comicId));
    }

    @GetMapping("/{comicId}/capitulos")
    public ResponseEntity<List<Chapter>> getChapters(@PathVariable Integer comicId) {
        return ResponseEntity.ok().body(chapterService.getChaptersByStoryId(comicId));
    }

    @GetMapping("/{comicId}/capitulos/{chapterId}")
    public ResponseEntity<Chapter> getChapter(@PathVariable Integer comicId, @PathVariable Integer chapterId) {

        return ResponseEntity.ok().body(chapterService.getChapter(comicId, chapterId));
    }

    @GetMapping("/{comicId}/capitulos/{chapterId}/paginas")
    public ResponseEntity<List<Page>> getPages(@PathVariable Integer comicId, @PathVariable Integer chapterId) {

        return ResponseEntity.ok().body(pageService.getPages(comicId, chapterId));
    }

    @GetMapping("/{comicId}/capitulos/{chapterId}/paginas/{pageId}")
    public ResponseEntity<Page> getPage(@PathVariable Integer comicId, @PathVariable Integer chapterId,
            @PathVariable Integer pageId) {

        return ResponseEntity.ok().body(pageService.getPage(comicId, chapterId, pageId));
    }

    @PostMapping("/{comicId}/capitulos")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> createChapter(@PathVariable Integer comicId,
            @RequestBody @Valid ChapterUpdateRequest request) throws URISyntaxException {

        Chapter chapter = chapterService.createChapter(comicId, chapterMapper.chapterUpdateRequestToChapter(request),
                ChapterService.TYPE_COMIC);

        return ResponseEntity.created(new URI("/comics/" + comicId + "/capitulos/" + chapter.getChapterId()))
                .body(chapter);
    }

    @PostMapping("/{comicId}/capitulos/{chapterId}/paginas")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> createPage(@PathVariable Integer comicId, @PathVariable Integer chapterId,
            @RequestBody @Valid PageUpdateRequest request) throws URISyntaxException {

        Page page = pageService.createPage(comicId, chapterId, pageMapper.pageUpdateRequestToPage(request));

        return ResponseEntity
                .created(new URI("/comics/" + comicId + "/capitulos/" + chapterId + "/" + page.getPageId())).body(page);
    }

    @PutMapping("/{comicId}/capitulos/{chapterId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> updateChapter(@PathVariable Integer comicId, @PathVariable Integer chapterId,
            @RequestBody @Valid ChapterUpdateRequest request) {

        return ResponseEntity.ok().body(
                chapterService.updateChapter(comicId, chapterId, chapterMapper.chapterUpdateRequestToChapter(request)));
    }

    @PutMapping("/{comicId}/capitulos/{chapterId}/paginas/{pageId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> updatePage(@PathVariable Integer comicId, @PathVariable Integer chapterId,
            @PathVariable Integer pageId, @RequestBody PageUpdateRequest request) {

        return ResponseEntity.ok()
                .body(pageService.updatePage(comicId, chapterId, pageId, pageMapper.pageUpdateRequestToPage(request)));
    }

    @DeleteMapping("/{comicId}/capitulos/{chapterId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> deleteChapter(@PathVariable Integer comicId, @PathVariable Integer chapterId) {

        return ResponseEntity.ok().body(chapterService.deleteChapter(comicId, chapterId));
    }

    @DeleteMapping("/{comicId}/capitulos/{chapterId}/paginas/{pageId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> deletePage(@PathVariable Integer comicId, @PathVariable Integer chapterId,
            @PathVariable Integer pageId) {

        return ResponseEntity.ok().body(pageService.deletePage(comicId, chapterId, pageId));
    }

    @PostMapping("/{comicId}/toggle-like")
    public ResponseEntity<Story> toggleLike(@PathVariable Integer comicId, Principal principal) {
        return ResponseEntity.ok().body(comicService.toggleLike(comicId, principal.getName()));
    }

}
