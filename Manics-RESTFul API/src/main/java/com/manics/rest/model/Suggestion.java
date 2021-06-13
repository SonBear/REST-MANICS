package com.manics.rest.model.request;

import java.sql.Date;
import java.util.List;


@Entity
@Table(name = "sugerencias")
public class Suggestion {

    @Id
    @Column(name = "suggestion_id")
    @GeneratedValue(strategy = GeneratuionType.IDENTITY)
    private Integer suggestionId; 


    @OneToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;
    

    @Column
    @NotNull
    private String content;

    @Column 
    @NotNull
    private Date creationDate;

    public Suggestion(Integer userId, String content, Date creationDate){
        this.userId  = userId;

    }


    public Integer getId(){
        return suggestionId;
    }

    public Integer getUser(){
        return userId;
    }

    public Sting getContent(){
        return content;
    }

    public Date getDate(){
        return fechaCreacion;
    }

    public String setContent(String content){
        this.content = content;
    }
    
    public Integer setUserId(Integer userId){
        this.userId = userId;
    }

    public Date getCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }




}
