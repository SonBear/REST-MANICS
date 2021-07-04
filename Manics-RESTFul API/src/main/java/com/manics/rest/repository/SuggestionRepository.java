package com.manics.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manics.rest.model.Suggestion;

@Repository
public interface SuggestionRepository extends CrudRepository<Suggestion, Integer> {

}