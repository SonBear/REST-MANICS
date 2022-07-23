package com.manics.rest.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import com.manics.rest.model.auth.User;

@Entity
@Table(name = "sugerencias")
public class Suggestion {
  @Id
  @Column(name = "suggestion_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer Id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  @JoinColumn(name = "user_id", nullable = false)
  @JsonBackReference
  private User user;

  @Column(name = "content")
  private String content;

  @Column(name = "creationDate")
  private ZonedDateTime creationDate = ZonedDateTime.now();

  public Suggestion() {

  }

  public Suggestion(User user, String content) {
    this.content = content;
    this.user = user;
  }

  public Integer getId() {
    return Id;
  }

  public User getUser() {
    return user;
  }

  public String getContent() {
    return content;
  }

  public ZonedDateTime getDate() {
    return creationDate;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void updateContent(Suggestion suggestion) {
    this.content = suggestion.getContent();
  }

  @Override
  public String toString() {
    return "Suggestion{" + "suggestionId=" + Id + ", user='" + user + '\'' + ", content='" + content + '\''
        + ", fecha de creacion='" + creationDate + '\'' + '}';
  }

}
