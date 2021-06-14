package com.manics.rest.model.core;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stories")
@Inheritance(strategy = InheritanceType.JOINED)
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_id")
    private Integer id;

    @Column
    private String name;

    @Column
    private String author;

    @Column
    private Integer publicationYear;

    @Column
    private Integer availableChapters;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL)
    private List<Chapter> chapters;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Integer getAvailableChapters() {
        return availableChapters;
    }

    public void setAvailableChapters(Integer availableChapters) {
        this.availableChapters = availableChapters;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public void updateStory(Category category, Story story) {
        setCategory(category);
        setName(story.getName());
        setAuthor(story.getAuthor());
        setAvailableChapters(story.getAvailableChapters());
        setPublicationYear(story.getPublicationYear());
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", availableChapters=" + availableChapters +
                ", category=" + category +
                ", chapters=" + chapters +
                '}';
    }

}