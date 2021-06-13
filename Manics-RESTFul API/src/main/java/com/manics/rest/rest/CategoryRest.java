package com.manics.rest.rest;

import java.util.List;

import javax.validation.Valid;

import com.manics.rest.model.Category;
import com.manics.rest.model.request.CategoryRequest;
import com.manics.rest.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryRest {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categorias")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.ok().body(categoryService.getCategories());
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Integer id){
        return ResponseEntity.ok().body(categoryService.getCategory(id));
    }

    @PostMapping("/categorias")
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CategoryRequest request){
        return ResponseEntity.ok().body(categoryService.createCategory(request));
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody @Valid CategoryRequest request){
        return ResponseEntity.ok().body(categoryService.updateCategory(id, request));
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer id){
        return ResponseEntity.ok().body(categoryService.deleteCategory(id));
    }

}
