package com.manics.rest.repository;

import com.manics.rest.model.Manga;
import com.manics.rest.model.core.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MangaRepository extends CrudRepository<Manga, Integer> {

    List<Manga> findAllByCategory(Category category);

}
