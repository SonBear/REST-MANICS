package com.manics.rest.model.request;
import com.manics.rest.model.Suggestion;
import com.manics.rest.model.User;
import java.sql.Date;
public class SuggestionRequest{

    private Integer userId;

    private String content; 

    public SuggestionRequest(){

    }

    public Integer getUserId(){
        return userId;
    }

    public String getContent(){
        return content;
    }

}