package com.yash.usermanagement.controller;

import com.yash.usermanagement.entity.Post;
import com.yash.usermanagement.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {

        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Post createPost(@Valid @RequestBody Post post) {
        return postService.createPost(post);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Post> createPostForUser(
            @PathVariable Long userId,
            @Valid @RequestBody Post post) {

        Post savedPost = postService.createPostForUser(userId, post);

        if (savedPost == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(savedPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody Post post) {

        Post updatedPost = postService.updatePost(id, post);

        if (updatedPost == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {

        boolean deleted = postService.deletePost(id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Post not found");
        }

        return ResponseEntity.ok("Post deleted successfully");
    }

    // Day 6 - Search Posts by Keyword (JPQL)
    @GetMapping("/search")
    public List<Post> searchPosts(@RequestParam String keyword) {
        return postService.searchPosts(keyword);
    }

    // Day 6 - Find Posts By User
    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUser(@PathVariable Long userId) {
        return postService.getPostsByUser(userId);
    }

    // Day 6 - Native SQL Search
    @GetMapping("/search-native")
    public List<Post> searchPostsNative(@RequestParam String keyword) {
        return postService.searchPostsNative(keyword);
    }

    // Day 6 Mini Task - Recent Posts
    @GetMapping("/recent")
    public List<Post> getRecentPosts() {
        return postService.getPostsCreatedLast7Days();
    }
}