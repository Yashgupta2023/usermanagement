package com.yash.usermanagement.controller;

import com.yash.usermanagement.entity.Post;
import com.yash.usermanagement.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Posts", description = "Post Management APIs")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Operation(summary = "Get all posts")
    @GetMapping
    public Page<Post> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return postService.getPosts(pageable);
    }

    @Operation(summary = "Get post by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {

        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a post")
    @PostMapping
    public Post createPost(@Valid @RequestBody Post post) {
        return postService.createPost(post);
    }

    @Operation(summary = "Create post for user")
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

    @Operation(summary = "Update post")
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

    @Operation(summary = "Delete post")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {

        boolean deleted = postService.deletePost(id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Post not found");
        }

        return ResponseEntity.ok("Post deleted successfully");
    }

    @Operation(summary = "Search posts")
    @GetMapping("/search")
    public List<Post> searchPosts(@RequestParam String keyword) {
        return postService.searchPosts(keyword);
    }

    @Operation(summary = "Get posts by user")
    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUser(@PathVariable Long userId) {
        return postService.getPostsByUser(userId);
    }

    @Operation(summary = "Native search")
    @GetMapping("/search-native")
    public List<Post> searchPostsNative(@RequestParam String keyword) {
        return postService.searchPostsNative(keyword);
    }

    @Operation(summary = "Get recent posts")
    @GetMapping("/recent")
    public List<Post> getRecentPosts() {
        return postService.getPostsCreatedLast7Days();
    }

    @Operation(summary = "Create dummy posts")
    @PostMapping("/dummy")
    public String createDummyPosts() {

        for (int i = 1; i <= 25; i++) {

            Post post = new Post();

            post.setTitle("Dummy Post " + i);
            post.setContent("Dummy Content " + i);

            postService.createPostForUser(2L, post);
        }

        return "25 Dummy Posts Created";
    }
}