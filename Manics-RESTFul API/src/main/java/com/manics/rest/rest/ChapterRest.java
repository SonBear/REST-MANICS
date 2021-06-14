package com.manics.rest.rest;

import com.manics.rest.mappers.ChapterMapper;
import com.manics.rest.model.core.Chapter;
import com.manics.rest.rest.request.chapter.ChapterRequest;
import com.manics.rest.rest.request.chapter.ChapterUpdateRequest;
import com.manics.rest.service.ChapterService;
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
@RequestMapping("capitulos")
public class ChapterRest {

    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;

    @Autowired
    public ChapterRest(ChapterService chapterService, ChapterMapper chapterMapper) {
        this.chapterService = chapterService;
        this.chapterMapper = chapterMapper;
    }

    @GetMapping
    public ResponseEntity<List<Chapter>> getChapters() {
        return ResponseEntity.ok().body(chapterService.getChapters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chapter> getChapterById(@PathVariable(name = "id") Integer chapterId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(chapterService.getChapterById(chapterId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> createChapter(@RequestBody @Valid ChapterRequest request) throws URISyntaxException {
        Chapter chapter = chapterService.createChapter(
                request.getStoryId(),
                chapterMapper.chapterRequestToChapter(request)
        );

        return ResponseEntity.created(new URI("/capitulos/" + chapter.getChapterId())).body(chapter);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> updateChapter(@PathVariable(name = "id") Integer chapterId,
                                                 @RequestBody @Valid ChapterUpdateRequest request) {

        Chapter chapter = chapterService.updateChapter(
                chapterId,
                chapterMapper.chapterUpdateRequestToChapter(request)
        );

        return ResponseEntity.ok().body(chapter);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> deleteChapter(@PathVariable(name = "id") Integer chapterId) {
        return ResponseEntity.ok().body(chapterService.deleteChapter(chapterId));
    }

}
