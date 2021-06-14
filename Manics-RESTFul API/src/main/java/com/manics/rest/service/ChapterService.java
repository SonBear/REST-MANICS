package com.manics.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.manics.rest.exception.BadRequestException;
import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.core.Chapter;
import com.manics.rest.model.core.Story;
import com.manics.rest.repository.ChapterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterService {
    
    private final ChapterRepository chapterRepository;
    private final StoryService storyService;

    @Autowired
    private ChapterService(ChapterRepository chapterRepository, StoryService storyService){
        this.chapterRepository = chapterRepository;
        this.storyService = storyService;
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
        if (Objects.isNull(storyId))
            throw new BadRequestException("Atributo storyId no incluido");

        Story story = storyService.getStoryById(storyId);
        chapter.setStory(story);
        chapterRepository.save(chapter);
        return chapter;
    }

    //Revisar por que no se borraaaa
    public Chapter updateChapter(Integer chapterId, Chapter newChapter){
        Chapter chapter = getChapterById(chapterId);       
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
