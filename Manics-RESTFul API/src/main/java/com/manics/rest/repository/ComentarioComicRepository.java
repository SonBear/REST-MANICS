package com.manics.rest.repository;

import com.manics.rest.model.ComentarioComic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioComicRepository extends CrudRepository<ComentarioComic, Integer>{
    
}
