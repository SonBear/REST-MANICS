package com.manics.rest.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.manics.rest.service.SuggestionService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import com.manics.rest.model.Suggestion;
import com.manics.rest.model.request.SuggestionRequest;;

@RestController 
public class SuggestionRest{

    @Autowired
    private SuggestionService suggestionService;

    @GetMapping("/sugerencias")
    public ResponseEntity<List<Suggestion>> getSugestions(){
        return ResponseEntity.ok().body(suggestionService.getSuggestion());
    }
    
    @GetMapping("/sugerencias/{id}")
    public ResponseEntity<Suggestion> getSuggestionById(@PathVariable(name = "id") Integer id){
        Suggestion suggestion = suggestionService.getSuggestionById(id); 
        return ResponseEntity.ok().body(suggestion);
    }

    @PostMapping("/sugerencias/{username}")
    public ResponseEntity<Suggestion> createSuggestion(@RequestBody @Valid SuggestionRequest request, @PathVariable(name = "username") String username) throws URISyntaxException{
        Suggestion suggestion = suggestionService.createSuggestion(request, username);
        return ResponseEntity.created(new URI("/sugerencias/" + suggestion.getId())).body(suggestion);
    }
}