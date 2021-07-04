package com.manics.rest.rest;

import com.manics.rest.mappers.ChapterMapper;
import com.manics.rest.mappers.PageMapper;
import com.manics.rest.mappers.StoryMapper;
import com.manics.rest.model.Manga;
import com.manics.rest.model.core.Chapter;
import com.manics.rest.model.core.Page;
import com.manics.rest.rest.request.StoryRequest;
import com.manics.rest.rest.request.chapter.ChapterUpdateRequest;
import com.manics.rest.rest.request.page.PageUpdateRequest;
import com.manics.rest.service.MangaService;
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
@RequestMapping("mangas")
public class MangaRest {

    private final MangaService mangaService;
    private final StoryMapper storyMapper;
    private final PageMapper pageMapper;
    private final ChapterMapper chapterMapper;

    @Autowired
    public MangaRest(MangaService mangaService, StoryMapper storyMapper, PageMapper pageMapper,
            ChapterMapper chapterMapper) {
        this.mangaService = mangaService;
        this.storyMapper = storyMapper;
        this.pageMapper = pageMapper;
        this.chapterMapper = chapterMapper;
    }

    @GetMapping
    public ResponseEntity<List<Manga>> getMangas() {
        return ResponseEntity.ok().body(mangaService.getMangas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manga> getMangaById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(mangaService.getMangaById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Manga> createManga(@RequestBody @Valid StoryRequest request) throws URISyntaxException {

        Manga manga = mangaService.createManga(request.getCategoryId(), storyMapper.storyRequestToManga(request));

        return ResponseEntity.created(new URI("/mangas/" + manga.getId())).body(manga);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Manga> updateManga(@PathVariable Integer id, @RequestBody @Valid StoryRequest request) {

        return ResponseEntity.ok()
                .body(mangaService.updateManga(id, request.getCategoryId(), storyMapper.storyRequestToManga(request)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Manga> deleteManga(@PathVariable(name = "id") Integer mangaId) {
        return ResponseEntity.ok().body(mangaService.deleteManga(mangaId));
    }

    // Fixed paths
    @GetMapping("/{id}/capitulos")
    public ResponseEntity<List<Chapter>> getMangaChapters(@PathVariable(name = "id") Integer mangaId) {
        return ResponseEntity.ok().body(mangaService.getAllChapters(mangaId));
    }

    @GetMapping("/{mangaId}/capitulos/{chapterId}/paginas")
    public ResponseEntity<List<Page>> getPagesOfMangeChapter(@PathVariable(name = "mangaId") Integer mangaId,
            @PathVariable(name = "chapterId") Integer chapterId) {
        return ResponseEntity.ok().body(mangaService.getAllPagesOfChapterManga(mangaId, chapterId));
    }

    @GetMapping("/{mangaId}/capitulos/{chapterId}")
    public ResponseEntity<Chapter> getChapter(@PathVariable(name = "mangaId") Integer mangaId,
            @PathVariable(name = "chapterId") Integer chapterId) {
        return ResponseEntity.ok().body(mangaService.getMangaChapter(mangaId, chapterId));
    }

    @GetMapping("/{mangaId}/capitulos/{chapterId}/paginas/{pageId}")
    public ResponseEntity<Page> getPageChapter(@PathVariable(name = "mangaId") Integer mangaId,
            @PathVariable(name = "chapterId") Integer chapterId, @PathVariable(name = "pageId") Integer pageId) {
        return ResponseEntity.ok().body(mangaService.getPageOfMangaChapter(mangaId, chapterId, pageId));
    }

    @PostMapping("/{mangaId}/capitulos")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> createChapter(@PathVariable(name = "mangaId") Integer mangaId,
            @RequestBody @Valid ChapterUpdateRequest request) throws URISyntaxException {
        Chapter chapter = mangaService.createChapterOfManga(mangaId,
                chapterMapper.chapterUpdateRequestToChapter(request));
        return ResponseEntity.created(new URI("/mangas/" + mangaId + "/capitulos/" + chapter.getChapterId()))
                .body(chapter);
    }

    @PostMapping("/{mangaId}/capitulos/{chapterId}/paginas")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> createPageChapter(@PathVariable(name = "mangaId") Integer mangaId,
            @PathVariable(name = "chapterId") Integer chapterId, @RequestBody @Valid PageUpdateRequest request)
            throws URISyntaxException {
        Page page = mangaService.createPageOfMangaChapter(mangaId, chapterId,
                pageMapper.pageUpdateRequestToPage(request));
        return ResponseEntity
                .created(new URI("/mangas/" + mangaId + "/capitulos/" + chapterId + "/" + page.getPageId())).body(page);

    }

    @PutMapping("/{mangaId}/capitulos/{chapterId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> updateChapter(@PathVariable(name = "mangaId") Integer mangaId,
            @PathVariable(name = "chapterId") Integer chapterId, @Valid ChapterUpdateRequest request) {
        return ResponseEntity.ok().body(mangaService.updateChapterOfManga(mangaId, chapterId,
                chapterMapper.chapterUpdateRequestToChapter(request)));
    }

    @PutMapping("/{mangaId}/capitulos/{chapterId}/paginas/{pageId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> updatePageChapter(@PathVariable(name = "mangaId") Integer mangaId,
            @PathVariable(name = "chapterId") Integer chapterId, @PathVariable(name = "pageId") Integer pageId,
            @RequestBody PageUpdateRequest request) {
        return ResponseEntity.ok().body(mangaService.updatePageOfChapterManga(mangaId, chapterId, pageId,
                pageMapper.pageUpdateRequestToPage(request)));
    }

    /**
     * No andan funcionando los deletes---
     * 
     * @param mangaId
     * @param chapterId
     * @return
     */
    @DeleteMapping("/{mangaId}/capitulos/{chapterId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> deleteChapter(@PathVariable(name = "mangaId") Integer mangaId,
            @PathVariable(name = "chapterId") Integer chapterId) {
        return ResponseEntity.ok().body(mangaService.deleteChapterOfManga(mangaId, chapterId));
    }

    @DeleteMapping("/{mangaId}/capitulos/{chapterId}/paginas/{pageId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> deletePageChapter(@PathVariable(name = "mangaId") Integer mangaId,
            @PathVariable(name = "chapterId") Integer chapterId, @PathVariable(name = "pageId") Integer pageId) {
        return ResponseEntity.ok().body(mangaService.deletePageOfChapterManga(mangaId, chapterId, pageId));
    }

}
