package com.manics.rest.repository;

import com.manics.rest.model.Manga;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MangaRepository extends CrudRepository<Manga, Integer>{
    
}
