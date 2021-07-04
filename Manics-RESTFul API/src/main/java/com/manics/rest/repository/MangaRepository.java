package com.manics.rest.repository;

import java.util.List;

import com.manics.rest.model.Manga;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MangaRepository extends CrudRepository<Manga, Integer> {
    public List<Manga> findByNameLike(String name);
}
