package com.yash.usermanagement.service;

import com.yash.usermanagement.entity.Post;
import com.yash.usermanagement.entity.User;
import com.yash.usermanagement.repository.PostRepository;
import com.yash.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post createPostForUser(Long userId, Post post) {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        post.setUser(user);

        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post updatedPost) {

        Post existingPost = postRepository.findById(id).orElse(null);

        if (existingPost == null) {
            return null;
        }

        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());

        return postRepository.save(existingPost);
    }

    public boolean deletePost(Long id) {

        if (!postRepository.existsById(id)) {
            return false;
        }

        postRepository.deleteById(id);
        return true;
    }

    // Day 6 - Search Posts By Keyword
    public List<Post> searchPosts(String keyword) {
        return postRepository.findPostsByKeyword(keyword);
    }

    // Day 6 - Find Posts By User
    public List<Post> getPostsByUser(Long userId) {
        return postRepository.findPostsByUserId(userId);
    }

    // Day 6 - Native SQL Search
    public List<Post> searchPostsNative(String keyword) {
        return postRepository.findPostsByKeywordNative(keyword);
    }

    // Day 6 Mini Task - Posts Created In Last 7 Days
    public List<Post> getPostsCreatedLast7Days() {

        LocalDateTime sevenDaysAgo =
                LocalDateTime.now().minusDays(7);

        return postRepository.findPostsCreatedAfter(sevenDaysAgo);
    }
}