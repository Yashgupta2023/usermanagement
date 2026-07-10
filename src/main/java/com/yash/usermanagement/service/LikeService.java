package com.yash.usermanagement.service;

import com.yash.usermanagement.entity.Like;
import com.yash.usermanagement.entity.Post;
import com.yash.usermanagement.entity.User;
import com.yash.usermanagement.exception.ResourceNotFoundException;
import com.yash.usermanagement.repository.LikeRepository;
import com.yash.usermanagement.repository.PostRepository;
import com.yash.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public Like likePost(Long userId, Long postId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + userId
                        ));

        Post post = postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Post not found with id: " + postId
                        ));

        Like like = new Like();

        like.setUser(user);

        like.setPost(post);

        return likeRepository.save(like);
    }

    public void unlikePost(Long likeId) {

        if (!likeRepository.existsById(likeId)) {

            throw new ResourceNotFoundException(
                    "Like not found with id: " + likeId
            );
        }

        likeRepository.deleteById(likeId);
    }

    public long countLikes(Long postId) {

        return likeRepository.countByPostId(postId);
    }

}