package com.manics.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.manics.rest.model.Category;
import com.manics.rest.model.manga.ChapterManga;
import com.manics.rest.model.manga.Manga;
import com.manics.rest.model.manga.PageManga;
import com.manics.rest.model.request.manga.ChapterMangaRequest;
import com.manics.rest.model.request.manga.MangaRequest;
import com.manics.rest.model.request.manga.PageMangaRequest;
import com.manics.rest.repository.manga.ChapterMangaRepository;
import com.manics.rest.repository.manga.MangaRepository;
import com.manics.rest.repository.manga.PageMangaRepository;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MangaService {
    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private ChapterMangaRepository chapterRepository;

    @Autowired
    private PageMangaRepository pageRepository;

    @Autowired 
    private CategoryService categoryService;

    public List<Manga> getMangas(){
        List<Manga> mangas = new ArrayList<>();
        mangaRepository.findAll().iterator().forEachRemaining(mangas::add);
        return mangas;
    }

    public Manga createManga(MangaRequest request) {
        Manga manga = new Manga();
        
        Category category = categoryService.getCategory(request.getCategoriaId());
        manga.setCategory(category);
        manga.setName(request.getName());
        manga.setAvailableChapters(request.getAvaiableChapters());
        manga.setPublicationYear(request.getPublicationYear());
        manga.setAuthor(request.getAuthor());
        mangaRepository.save(manga);

        saveChaptersFromRequestChapter(request.getChapters(), manga);
        return manga;
    }

    private void saveChaptersFromRequestChapter(List<ChapterMangaRequest> chapters, Manga manga) {
        
       chapters.stream().forEach((ChapterMangaRequest chapter)->{
            ChapterManga ch = new ChapterManga();
            ch.setChapterNumber(chapter.getChapterNumber());
            ch.setName(chapter.getName());
            ch.setPageTotal(chapter.getPageTotal());
           
            ch.setPublicationDate(chapter.getPublicationDate());
            ch.setManga(manga);

           chapterRepository.save(ch);
           savePagesFromRequestPage(chapter.getPages(), ch);
        });

    }

    private void savePagesFromRequestPage(List<PageMangaRequest> pages, ChapterManga chapter) {
      pages.stream().forEach((PageMangaRequest pageRequest)->{
            PageManga page = new PageManga();
            page.setChapter(chapter);
            page.setImageUrl(pageRequest.getImageUrl());
            page.setPageNumber(pageRequest.getPageNumber());
            pageRepository.save(page);
        });
    }

    public Manga getManga(Integer id){
        return mangaRepository.findById(id).orElseThrow(()->new RuntimeException("dfasd"));
    }

    public Manga updateManga(Integer id, MangaRequest request){
        Manga manga = getManga(id);
        
        Category category = categoryService.getCategory(request.getCategoriaId());
        manga.setCategory(category);
        manga.setAuthor(request.getAuthor());
        manga.setAvailableChapters(request.getAvaiableChapters());
        manga.setName(request.getName());
        manga.setPublicationYear(request.getPublicationYear());
        updateChapters(manga.getChapters(), request.getChapters());

        return manga;
    }

    private void updateChapters(List<ChapterManga> chapters, List<ChapterMangaRequest> chapterRequests){
        IntStream.range(0, chapters.size()).forEach(i->{
            ChapterManga chapter = chapters.get(i);
            ChapterMangaRequest chapterRequest = chapterRequests.get(i);
            if(!Objects.isNull(chapterRequest)){
                chapter.setChapterNumber(chapterRequest.getChapterNumber());
                chapter.setName(chapterRequest.getName());
                chapter.setPageTotal(chapterRequest.getPageTotal());
                chapter.setPublicationDate(chapterRequest.getPublicationDate());
                chapterRepository.save(chapter);
                updatePages(chapter.getPages(), chapterRequest.getPages());
            }
        });
    }

    private void updatePages(List<PageManga> pages, List<PageMangaRequest> pageRequests){
        IntStream.range(0, pages.size()).forEach(i -> {
            PageManga page = pages.get(i);
            PageMangaRequest pageRequest = pageRequests.get(i);
            if (!Objects.isNull(pageRequest)) {
                page.setImageUrl(pageRequest.getImageUrl());
                page.setPageNumber(pageRequest.getPageNumber());
                pageRepository.save(page);
            }
        });
    }

    public Manga deleteManga(Integer id){
        Manga manga = getManga(id);
        manga.getChapters().stream().forEach((chapter)->{
            chapter.getPages().stream().forEach((page)->{
                pageRepository.delete(page);
            });
            chapterRepository.delete(chapter);
        });
        mangaRepository.delete(manga);
        return manga;
    }

    

    public Boolean hasCategory(Category category){
        return mangaRepository.findAllByCategory(category).size() > 0;
    }
}
