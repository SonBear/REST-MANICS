package com.manics.rest.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mangas_capitulos")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "capitulo_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "manga_id")
    @JsonBackReference
    private Manga manga;

    @Column(name = "numero_capitulo")
    private Integer chapterNumber;

    @Column(name = "nombre")
    private String name;

    @Column(name = "fecha_publicacion")
    private Date publicationDate;

    @Column(name="total_paginas")
    private Integer pageTotal;

    @OneToMany(mappedBy = "chapter")
    private List<Page> pages;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public Manga getManga() {
        return manga;
    }

    public void setManga(Manga manga) {
        this.manga = manga;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(Integer chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    @Override
    public String toString() {
        return "Chapter [chapterNumber=" + chapterNumber + ", id=" + id + ", manga=" + manga + ", name=" + name
                + ", pageTotal=" + pageTotal + ", pages=" + pages + ", publicationDate=" + publicationDate + "]";
    }

}
