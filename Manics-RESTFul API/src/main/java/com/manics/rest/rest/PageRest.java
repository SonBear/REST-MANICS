package com.manics.rest.rest;

import com.manics.rest.mappers.PageMapper;
import com.manics.rest.model.core.Page;
import com.manics.rest.rest.request.page.PageRequest;
import com.manics.rest.rest.request.page.PageUpdateRequest;
import com.manics.rest.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("paginas")
public class PageRest {

    private final PageService pageService;
    private final PageMapper pageMapper;

    @Autowired
    public PageRest(PageService pageService, PageMapper pageMapper) {
        this.pageService = pageService;
        this.pageMapper = pageMapper;
    }

    @GetMapping
    public ResponseEntity<List<Page>> getPages() {
        return ResponseEntity.ok().body(pageService.getPages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page> getPageById(@PathVariable(name = "id") Integer pageId) {
        return ResponseEntity.ok().body(pageService.getPageById(pageId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> createPage(@RequestBody @Valid PageRequest request) throws URISyntaxException {
        Page page = pageService.createPage(request.getChapterId(), pageMapper.pageRequestToPage(request));
        return ResponseEntity.created(new URI("/paginas/" + page.getPageId())).body(page);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> updatePage(@PathVariable(name = "id") Integer pageId,
                                           @RequestBody @Valid PageUpdateRequest request) {

        Page page = pageService.updatePage(pageId, pageMapper.pageUpdateRequestToPage(request));

        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> deletePage(@PathVariable(name = "id") Integer pageId) {
        Page page = pageService.deletePage(pageId);
        return ResponseEntity.ok().body(page);
    }

}
