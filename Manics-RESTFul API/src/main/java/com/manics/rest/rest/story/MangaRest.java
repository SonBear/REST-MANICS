package com.manics.rest.rest.story;

import com.manics.rest.mappers.ChapterMapper;
import com.manics.rest.mappers.PageMapper;
import com.manics.rest.mappers.StoryMapper;
import com.manics.rest.model.Manga;
import com.manics.rest.model.core.Chapter;
import com.manics.rest.model.core.Page;
import com.manics.rest.model.core.Story;
import com.manics.rest.rest.request.StoryRequest;
import com.manics.rest.rest.request.chapter.ChapterUpdateRequest;
import com.manics.rest.rest.request.page.PageUpdateRequest;
import com.manics.rest.service.stories.ChapterService;
import com.manics.rest.service.stories.PageService;
import com.manics.rest.service.stories.MangaService;
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
@RequestMapping("mangas")
public class MangaRest {

    private final MangaService mangaService;
    private final ChapterService chapterService;
    private final PageService pageService;

    private final StoryMapper storyMapper;
    private final PageMapper pageMapper;
    private final ChapterMapper chapterMapper;

    @Autowired
    public MangaRest(MangaService mangaService,
                     ChapterService chapterService,
                     PageService pageService,
                     StoryMapper storyMapper,
                     PageMapper pageMapper,
                     ChapterMapper chapterMapper) {

        this.mangaService = mangaService;
        this.chapterService = chapterService;
        this.pageService = pageService;

        this.storyMapper = storyMapper;
        this.pageMapper = pageMapper;
        this.chapterMapper = chapterMapper;
    }

    @GetMapping
    public ResponseEntity<List<Manga>> getMangas() {
        return ResponseEntity.ok().body(mangaService.getMangas());
    }

    @GetMapping("/{mangaId}")
    public ResponseEntity<Manga> getMangaById(@PathVariable Integer mangaId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(mangaService.getMangaById(mangaId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Manga> createManga(@RequestBody @Valid StoryRequest request) throws URISyntaxException {

        Manga manga = mangaService.createManga(request.getCategoryId(), storyMapper.storyRequestToManga(request));

        return ResponseEntity.created(new URI("/mangas/" + manga.getId())).body(manga);
    }

    @PutMapping("/{mangaId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Manga> updateManga(@PathVariable Integer mangaId,
                                             @RequestBody @Valid StoryRequest request) {

        return ResponseEntity.ok()
                .body(
                        mangaService.updateManga(
                                mangaId,
                                request.getCategoryId(),
                                storyMapper.storyRequestToManga(request))
                );
    }

    @DeleteMapping("/{mangaId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Manga> deleteManga(@PathVariable Integer mangaId) {
        return ResponseEntity.ok().body(mangaService.deleteManga(mangaId));
    }

    @GetMapping("/{mangaId}/capitulos")
    public ResponseEntity<List<Chapter>> getChapters(@PathVariable Integer mangaId) {
        return ResponseEntity.ok().body(chapterService.getChaptersByStoryId(mangaId));
    }

    @GetMapping("/{mangaId}/capitulos/{chapterId}")
    public ResponseEntity<Chapter> getChapter(@PathVariable Integer mangaId,
                                              @PathVariable Integer chapterId) {

        return ResponseEntity.ok().body(chapterService.getChapter(mangaId, chapterId));
    }

    @GetMapping("/{mangaId}/capitulos/{chapterId}/paginas")
    public ResponseEntity<List<Page>> getPages(@PathVariable Integer mangaId,
                                               @PathVariable Integer chapterId) {

        return ResponseEntity.ok().body(pageService.getPages(mangaId, chapterId));
    }

    @GetMapping("/{mangaId}/capitulos/{chapterId}/paginas/{pageId}")
    public ResponseEntity<Page> getPage(@PathVariable Integer mangaId,
                                        @PathVariable Integer chapterId,
                                        @PathVariable Integer pageId) {

        return ResponseEntity.ok().body(pageService.getPage(mangaId, chapterId, pageId));
    }

    @PostMapping("/{mangaId}/capitulos")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> createChapter(@PathVariable Integer mangaId,
                                                 @RequestBody @Valid ChapterUpdateRequest request) throws URISyntaxException {

        Chapter chapter = chapterService.createChapter(
                mangaId,
                chapterMapper.chapterUpdateRequestToChapter(request)
        );

        return ResponseEntity
                .created(
                        new URI("/mangas/" + mangaId + "/capitulos/" + chapter.getChapterId())
                ).body(chapter);
    }

    @PostMapping("/{mangaId}/capitulos/{chapterId}/paginas")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> createPage(@PathVariable Integer mangaId,
                                           @PathVariable Integer chapterId,
                                           @RequestBody @Valid PageUpdateRequest request) throws URISyntaxException {

        Page page = pageService.createPage(
                mangaId,
                chapterId,
                pageMapper.pageUpdateRequestToPage(request)
        );

        return ResponseEntity
                .created(
                        new URI("/mangas/" + mangaId + "/capitulos/" + chapterId + "/" + page.getPageId())
                ).body(page);
    }

    @PutMapping("/{mangaId}/capitulos/{chapterId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> updateChapter(@PathVariable Integer mangaId,
                                                 @PathVariable Integer chapterId,
                                                 @RequestBody @Valid ChapterUpdateRequest request) {

        return ResponseEntity.ok().body(chapterService.updateChapter(
                mangaId,
                chapterId,
                chapterMapper.chapterUpdateRequestToChapter(request))
        );
    }

    @PutMapping("/{mangaId}/capitulos/{chapterId}/paginas/{pageId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> updatePage(@PathVariable Integer mangaId,
                                           @PathVariable Integer chapterId,
                                           @PathVariable Integer pageId,
                                           @RequestBody PageUpdateRequest request) {

        return ResponseEntity.ok().body(pageService.updatePage(
                mangaId,
                chapterId,
                pageId,
                pageMapper.pageUpdateRequestToPage(request))
        );
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
    public ResponseEntity<Chapter> deleteChapter(@PathVariable Integer mangaId,
                                                 @PathVariable Integer chapterId) {

        return ResponseEntity.ok().body(chapterService.deleteChapter(mangaId, chapterId));
    }

    @DeleteMapping("/{mangaId}/capitulos/{chapterId}/paginas/{pageId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> deletePage(@PathVariable Integer mangaId,
                                           @PathVariable Integer chapterId,
                                           @PathVariable Integer pageId) {

        return ResponseEntity.ok().body(pageService.deletePage(mangaId, chapterId, pageId));
    }

    @PostMapping("/{mangaId}/toggle-like")
    public ResponseEntity<Story> toggleLike(@PathVariable Integer mangaId, Principal principal) {
        return ResponseEntity.ok().body(mangaService.toggleLike(mangaId, principal.getName()));
    }

}
