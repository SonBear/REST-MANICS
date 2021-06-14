package com.manics.rest.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import com.manics.rest.mappers.ChapterMapper;
import com.manics.rest.model.core.Chapter;
import com.manics.rest.rest.request.ChapterRequest;
import com.manics.rest.service.ChapterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("capitulos")
public class ChapterRest {
    
    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;

    @Autowired
    private ChapterRest(ChapterService chapterService, ChapterMapper chapterMapper) {
        this.chapterService = chapterService;
        this.chapterMapper = chapterMapper;
    }

    @GetMapping
    public ResponseEntity<List<Chapter>> getChapters(){
        return ResponseEntity.ok().body(chapterService.getChapters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chapter> getChapterById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.FOUND).body(chapterService.getChapterById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> createChapter(@RequestBody @Valid ChapterRequest request) throws URISyntaxException{
        Chapter chapter = chapterService.createChapter(request.getStoryId(), chapterMapper.chapterRquestToChapter(request));
        return ResponseEntity.created(new URI("/capitulos/" + chapter.getChapterId())).body(chapter);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> updateChapter(@PathVariable Integer id, @RequestBody @Valid ChapterRequest request){
        Chapter chapter = chapterService.updateChapter(id, chapterMapper.chapterRquestToChapter(request));
        return ResponseEntity.ok().body(chapter);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Chapter> deleteChapter(@PathVariable Integer id){
        return ResponseEntity.ok().body(chapterService.deleteChapter(id));
    }

}
