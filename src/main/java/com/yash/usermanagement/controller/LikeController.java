package com.yash.usermanagement.controller;

import com.yash.usermanagement.entity.Like;
import com.yash.usermanagement.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@Tag(name = "Likes", description = "Like Management APIs")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Operation(summary = "Like a post")
    @PostMapping("/post/{postId}/user/{userId}")
    public Like likePost(
            @PathVariable Long postId,
            @PathVariable Long userId) {

        return likeService.likePost(userId, postId);
    }

    @Operation(summary = "Unlike a post")
    @DeleteMapping("/{likeId}")
    public String unlikePost(
            @PathVariable Long likeId) {

        likeService.unlikePost(likeId);

        return "Like removed successfully";
    }

    @Operation(summary = "Count likes on a post")
    @GetMapping("/post/{postId}")
    public long countLikes(
            @PathVariable Long postId) {

        return likeService.countLikes(postId);
    }

}