package com.manics.rest.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "mangas")
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manga_id")
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "anio_publicacion")
    private Integer publicationYear;

    @Column(name = "capitulos_disponibles")
    private Integer availableChapters;

    @OneToOne
    @JoinColumn(name = "categoria_id")
    private Category categoria;

    @OneToMany(mappedBy = "manga")
    private List<Chapter> Chapters;

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

    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }

    public List<Chapter> getChapters() {
        return Chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        Chapters = chapters;
    }

    @Override
    public String toString() {
        return "Manga [Chapters=" + Chapters + ", availableChapters=" + availableChapters + ", categoria=" + categoria
                + ", id=" + id + ", name=" + name + ", publicationDate=" + publicationYear + "]";
    }


}
