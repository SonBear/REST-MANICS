package com.manics.rest.repository;

import com.manics.rest.model.Comic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends CrudRepository<Comic, Integer> {


}
