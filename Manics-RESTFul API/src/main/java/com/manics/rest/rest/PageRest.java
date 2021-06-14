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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageRest {
   
    private final  PageService pageService;
    
    private final PageMapper pageMapper;

    @Autowired
    private PageRest(PageService pageService, PageMapper pageMapper) {
        this.pageService = pageService;
        this.pageMapper = pageMapper;
    }
    
    @GetMapping("/paginas")
    public ResponseEntity<List<Page>> getPages(){
        return ResponseEntity.ok().body(pageService.getPages());
    }


    @GetMapping("/paginas/{id}")
    public ResponseEntity<Page> getPageById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(pageService.getPageById(id));
    }

    @PostMapping("/paginas")
    public ResponseEntity<Page> createPage(@RequestBody @Valid PageRequest request) throws URISyntaxException {
        Page page = pageService.createPage(request.getChapterId(), pageMapper.pageRequestToPage(request));
        return ResponseEntity.created(new URI("/paginas/" + page.getPageId())).body(page);
    }

    @PutMapping("/paginas/{id}")
    public ResponseEntity<Page> updatePage(@PathVariable Integer id, @RequestBody @Valid PageRequest request) {
        Page page = pageService.updatePage(id, pageMapper.pageRequestToPage(request));
        return ResponseEntity.ok().body(page);
    }//F no funiona

    @DeleteMapping("/paginas/{id}")
    public ResponseEntity<Page> deletePage(@PathVariable Integer id) {
        Page page = pageService.deletePage(id);
        return ResponseEntity.ok().body(page);
    }
}
