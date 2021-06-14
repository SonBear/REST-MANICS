package com.manics.rest.rest;

import com.manics.rest.mappers.CommentMapper;
import com.manics.rest.model.core.Comment;
import com.manics.rest.rest.request.CommentRequest;
import com.manics.rest.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("comentarios")
public class CommentRest {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentRest(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable(name = "id") Integer commentId) {
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }

    @GetMapping("/stories/{id}")
    public ResponseEntity<List<Comment>> getCommentsByStoryId(@PathVariable(name = "id") Integer storyId) {
        return ResponseEntity.ok(commentService.getCommentsByStoryId(storyId));
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable(name = "id") Integer userId) {
        return ResponseEntity.ok(commentService.getCommentsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody @Valid CommentRequest commentRequest)
            throws URISyntaxException {

        Comment comment = commentService.createComment(commentMapper.commentRequestToComment(commentRequest));

        return ResponseEntity
                .created(
                        new URI(String.format("/comentarios/%d", comment.getId()))
                )
                .body(comment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable(name = "id") Integer commentId,
                                                 @RequestBody @Valid CommentRequest commentRequest) {

        return ResponseEntity.ok(
                commentService.updateComment(commentId, commentMapper.commentRequestToComment(commentRequest))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable(name = "id") Integer commentId) {
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }

}
