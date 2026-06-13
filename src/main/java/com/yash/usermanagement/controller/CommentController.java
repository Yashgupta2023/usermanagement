package com.yash.usermanagement.controller;

import com.yash.usermanagement.entity.Comment;
import com.yash.usermanagement.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @PostMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<Comment> createComment(
            @PathVariable Long userId,
            @PathVariable Long postId,
            @Valid @RequestBody Comment comment) {

        Comment savedComment =
                commentService.createComment(userId, postId, comment);

        if (savedComment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(savedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {

        boolean deleted = commentService.deleteComment(id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Comment not found");
        }

        return ResponseEntity.ok("Comment deleted successfully");
    }
}