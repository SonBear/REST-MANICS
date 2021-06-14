package com.manics.rest.rest;

import com.manics.rest.mappers.CategoryMapper;
import com.manics.rest.model.core.Category;
import com.manics.rest.rest.request.CategoryRequest;
import com.manics.rest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryRest {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryRest(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok().body(categoryService.getCategories());
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Integer id) {
        return ResponseEntity.ok().body(categoryService.getCategory(id));
    }

    @PostMapping("/categorias")
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CategoryRequest request) {
        return ResponseEntity.ok().body(
                categoryService.createCategory(categoryMapper.categoryRequestToCategory(request))
        );
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id,
                                                   @RequestBody @Valid CategoryRequest request) {

        return ResponseEntity.ok().body(
                categoryService.updateCategory(id, categoryMapper.categoryRequestToCategory(request))
        );
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer id) {
        return ResponseEntity.ok().body(categoryService.deleteCategory(id));
    }

}
