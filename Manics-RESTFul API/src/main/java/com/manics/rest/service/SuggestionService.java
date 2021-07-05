package com.manics.rest.service;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.Suggestion;
import com.manics.rest.model.auth.User;
import com.manics.rest.repository.SuggestionRepository;
import com.manics.rest.repository.UserRepository;
import com.manics.rest.rest.request.user.SuggestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuggestionService {

    @Autowired
    private SuggestionRepository suggestionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Suggestion> getSuggestion() {
        List<Suggestion> suggestions = new ArrayList<>();
        suggestionRepository.findAll().iterator().forEachRemaining(suggestions::add);
        return suggestions;
    }

    public Suggestion getSuggestionById(Integer suggestionId) {
        return suggestionRepository.findById(suggestionId)
                .orElseThrow(() -> new NotFoundException("No se encontro la sugerencia con Id " + suggestionId));
    }

    public Suggestion createSuggestion(SuggestionRequest req, String username) {
        User user = userRepository.findByUsername(username);
        Suggestion suggestion = new Suggestion(user, req.getContent());
        Suggestion result = suggestionRepository.save(suggestion);
        return result;
    }

    public Suggestion updateSuggestion(SuggestionRequest req, Integer id) {
        Suggestion suggestion = getSuggestionById(id);
        suggestion.setContent(req.getContent());
        return suggestionRepository.save(suggestion);
    }

    public Suggestion deleteSuggestion(Integer id) {
        Suggestion suggestion = getSuggestionById(id);

        suggestionRepository.delete(suggestion);
        return suggestion;
    }

}