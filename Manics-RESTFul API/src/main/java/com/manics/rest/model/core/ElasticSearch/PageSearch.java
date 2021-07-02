package com.manics.rest.model.core.ElasticSearch;

import java.util.Arrays;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "pages")
public class PageSearch {

    @Id
    private Integer id;

    @Field(name = "url_image", type = FieldType.Text)
    private String urlImage;

    @Field(name = "vector_values", type = FieldType.Dense_Vector, dims = 50)
    private Double[] vector;

    public Integer getPageId() {
        return id;
    }

    public void setPageId(Integer pageId) {
        this.id = pageId;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Double[] getVector() {
        return vector;
    }

    public void setVector(Double[] vector) {
        this.vector = vector;
    }

    @Override
    public String toString() {
        return "PageSearch [pageId=" + id + ", urlImage=" + urlImage + ", vector=" + Arrays.toString(vector) + "]";
    }

}
