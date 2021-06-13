package com.manics.rest.service;

import java.util.ArrayList;
import java.util.List;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.request.SuggestionRequest;
import com.manics.rest.repository.SuggestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class SuggestionService{
    @Autowired
    SuggestionRepository suggestionRepository;

    public List<Suggestion> getSuggestion(){
        List<Suggestion> suggestions = new ArrayList<>();

        suggestionRepository.findAll().iterator().forEachRemainig(suggestions::add);
        return suggestions;
    }

    public Suggestion getSuggestionById(Integer suggestionId){
        return suggestionRepository
                .findById(suggestionId)
                .orElseThrow(
                    () -> new NotFoundException("No se encontro la sugerencia con Id " + suggestionId)
                )
    }

    public Suggestion createSuggestion(SuggestionRequest suggestionReq){
        Suggestion suggestion = new Suggestion();

    }

}