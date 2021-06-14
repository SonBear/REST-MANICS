package com.manics.rest.service;

import java.util.ArrayList;
import java.util.List;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.core.Chapter;
import com.manics.rest.model.core.Story;
import com.manics.rest.repository.ChapterRepository;
import com.manics.rest.repository.PageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterService {
    
    private final ChapterRepository chapterRepository;
    private final StoryService storyService;
    private final PageRepository pageRepository;

    @Autowired
    private ChapterService(ChapterRepository chapterRepository, StoryService storyService
    ,PageRepository pageRepository){
        this.chapterRepository = chapterRepository;
        this.storyService = storyService;
      this.pageRepository = pageRepository;
    }

    public List<Chapter> getChapters(){
        List<Chapter> chapters = new ArrayList<>();
        chapterRepository.findAll().iterator().forEachRemaining(chapters::add);;
        return chapters;
    }

    public Chapter getChapterById(Integer id){
        return chapterRepository.findById(id).orElseThrow(
        ()-> new NotFoundException(String.format("El capitulo con el id: %d no existe", id)));
    }
   
    public Chapter createChapter(Integer storyId, Chapter chapter){
        Story story = storyService.getStoryById(storyId);
        chapter.setStory(story);
        chapterRepository.save(chapter);
        return chapter;
    }

    //Revisar por que no se borraaaa
    public Chapter updateChapter(Integer chapterId, Chapter newChapter){
        Chapter chapter = getChapterById(chapterId);
       
        chapter.getPages().forEach((page)->{
            pageRepository.delete(page);
            System.out.println(page.getPageNumber());
        });
        newChapter.getPages().forEach((page)->
            page.setChapter(chapter)
        );
        chapter.updateChapter(newChapter);
        chapterRepository.save(chapter);
        return chapter;
    }

    public Chapter deleteChapter(Integer chapterId){
        Chapter chapter = getChapterById(chapterId);
        chapterRepository.delete(chapter);
        return chapter;
    }
}
