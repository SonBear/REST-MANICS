package com.manics.rest.model.core.ElasticSearch;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "stories")
public class StorySearch {
    @Id
    private Integer id;

    @Field(name = "name", type = FieldType.Text)
    private String name;

    public Integer getStoryId() {
        return id;
    }

    public void setStoryId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
