package com.manics.rest.model.core.ElasticSearch;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "stories", createIndex = true)
public class StorySearch {
    @Id
    private Integer id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String catagory;

    @Field(type = FieldType.Nested)
    private List<PageSearch> pages;

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

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public List<PageSearch> getPages() {
        return pages;
    }

    public void setPages(List<PageSearch> pages) {
        this.pages = pages;
    }

}
