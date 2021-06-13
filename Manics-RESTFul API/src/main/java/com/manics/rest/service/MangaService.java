package com.manics.rest.service;

import com.manics.rest.model.Manga;
import com.manics.rest.model.request.MangaRequest;
import com.manics.rest.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MangaService {

    @Autowired
    private MangaRepository mangaRepository;

    public List<Manga> getMangas() {
        List<Manga> mangas = new ArrayList<>();
        mangaRepository.findAll().iterator().forEachRemaining(mangas::add);
        return mangas;
    }

    public Manga createManga(MangaRequest request) {
        return null;
    }
}
