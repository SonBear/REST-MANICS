package com.manics.rest.model.manga;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.manics.rest.model.Category;


@Entity
@Table(name="mangas")
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manga_id")
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "autor")
    private String author;
    
    @Column(name="anio_publicacion")
    private Integer publicationYear;

    @Column(name = "capitulos_disponibles")
    private Integer availableChapters;

    @OneToOne
    @JoinColumn(name = "categoria_id")
    private Category category;

    @OneToMany(mappedBy = "manga")
    private List<ChapterManga> Chapters;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<ChapterManga> getChapters() {
        return Chapters;
    }

    public void setChapters(List<ChapterManga> chapters) {
        Chapters = chapters;
    }
    

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Manga [Chapters=" + Chapters + ", availableChapters=" + availableChapters + ", categoria=" + category
                + ", id=" + id + ", name=" + name + ", publicationDate=" + publicationYear + "]";
    }

    
}
