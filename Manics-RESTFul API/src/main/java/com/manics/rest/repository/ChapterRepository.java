package com.manics.rest.repository;

import com.manics.rest.model.core.Chapter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends CrudRepository<Chapter, Integer>{
    
}
