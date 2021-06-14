package com.manics.rest.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import com.manics.rest.mappers.PageMapper;
import com.manics.rest.model.core.Page;
import com.manics.rest.rest.request.PageRequest;
import com.manics.rest.service.PageService;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("paginas")
public class PageRest {
   
    private final  PageService pageService;
    private final PageMapper pageMapper;

    @Autowired
    public PageRest(PageService pageService, PageMapper pageMapper) {
        this.pageService = pageService;
        this.pageMapper = pageMapper;
    }
    
    @GetMapping
    public ResponseEntity<List<Page>> getPages(){
        return ResponseEntity.ok().body(pageService.getPages());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Page> getPageById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(pageService.getPageById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> createPage(@RequestBody @Valid PageRequest request) throws URISyntaxException {
        Page page = pageService.createPage(request.getChapterId(), pageMapper.pageRequestToPage(request));
        return ResponseEntity.created(new URI("/paginas/" + page.getPageId())).body(page);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> updatePage(@PathVariable Integer id, @RequestBody @Valid PageRequest request) {
        Page page = pageService.updatePage(id, pageMapper.pageRequestToPage(request));
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page> deletePage(@PathVariable Integer id) {
        Page page = pageService.deletePage(id);
        return ResponseEntity.ok().body(page);
    }
}
