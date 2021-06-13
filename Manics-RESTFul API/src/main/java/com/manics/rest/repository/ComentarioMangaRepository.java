package com.manics.rest.repository;

import com.manics.rest.model.ComentarioManga;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioMangaRepository extends CrudRepository<ComentarioManga, Integer>{
    
}
