package com.manics.rest.service;

import com.manics.rest.exception.NotFoundException;
import com.manics.rest.model.core.Comment;
import com.manics.rest.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final StoryService storyService;

    @Autowired
    public CommentService(CommentRepository commentRepository,
                          UserService userService,
                          StoryService storyService) {

        this.commentRepository = commentRepository;
        this.userService = userService;
        this.storyService = storyService;
    }

    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<>();
        commentRepository.findAll().iterator().forEachRemaining(comments::add);
        return comments;
    }

    public Comment getCommentById(Integer commentId) {
        return commentRepository
                .findById(commentId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("No encontramos el comentario con el id: %d", commentId))
                );
    }

    public List<Comment> getCommentsByStoryId(Integer storyId) {
        return commentRepository.findByStoryId(storyId);
    }

    public List<Comment> getCommentsByUserId(Integer userId) {
        return commentRepository.findByUserId(userId);
    }

    public Comment createComment(Comment comment) {
        userService.checkIfUserExist(comment.getUserId());
        storyService.checkIfStoryExists(comment.getStoryId());

        return commentRepository.save(comment);
    }

    public Comment updateComment(Integer commentId, Comment newComment) {
        Comment comment = getCommentById(commentId);

        comment.setContent(newComment.getContent());

        return commentRepository.save(comment);
    }

    public Comment deleteComment(Integer commentId) {
        Comment comment = getCommentById(commentId);

        commentRepository.delete(comment);

        return comment;
    }

}
