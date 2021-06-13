package com.manics.rest.repository.manga;

import com.manics.rest.model.manga.PageManga;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageMangaRepository extends CrudRepository<PageManga, Integer>{
    
}
