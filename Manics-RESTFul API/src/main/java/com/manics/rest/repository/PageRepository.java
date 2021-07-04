package com.manics.rest.repository;

import com.manics.rest.model.core.Page;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends CrudRepository<Page, Integer> {

}
