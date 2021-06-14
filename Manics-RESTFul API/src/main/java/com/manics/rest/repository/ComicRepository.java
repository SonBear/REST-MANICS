package com.manics.rest.repository;

import java.util.List;

import com.manics.rest.model.Comic;
import com.manics.rest.model.core.Category;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends CrudRepository<Comic, Integer> {

    List<Comic> findAllByCategory(Category category);
}
