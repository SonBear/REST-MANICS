package com.manics.rest.service;

import java.util.ArrayList;
import java.util.List;

import com.manics.rest.model.Category;
import com.manics.rest.model.request.CategoryRequest;
import com.manics.rest.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MangaService mangaService;

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().iterator().forEachRemaining(categories::add);
        return categories;
    }

    public Category getCategory(Integer id) {
        
        return categoryRepository.findById(id).orElseThrow(()->new RuntimeException("No found"));
    }

    public Category createCategory(CategoryRequest request){
        Category category = new Category();
        
        category.setDescription(request.getDescription());
        category.setName(request.getName());
        System.out.println(category);
        categoryRepository.save(category);
      
        return category;
    }

    public Category updateCategory(Integer id, CategoryRequest request) {
        Category category = getCategory(id);
        category.setDescription(request.getDescription());
        category.setName(request.getName());
        categoryRepository.save(category);
        return category;
    }

    public Category deleteCategory(Integer id) {
        Category category = getCategory(id);
        if(mangaService.hasCategory(category))
                throw new RuntimeException("La categoria no puede eliminarse");
        categoryRepository.delete(category);
        return category;
    }
    

}
