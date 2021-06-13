package com.manics.rest.repository.manga;

import java.util.List;

import com.manics.rest.model.Category;
import com.manics.rest.model.manga.Manga;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MangaRepository extends CrudRepository<Manga, Integer>{
    public List<Manga> findAllByCategory(Category category);
}
