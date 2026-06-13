package com.yash.usermanagement.service;

import com.yash.usermanagement.entity.Comment;
import com.yash.usermanagement.entity.Post;
import com.yash.usermanagement.entity.User;
import com.yash.usermanagement.repository.CommentRepository;
import com.yash.usermanagement.repository.PostRepository;
import com.yash.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment createComment(Long userId, Long postId, Comment comment) {

        User user = userRepository.findById(userId).orElse(null);
        Post post = postRepository.findById(postId).orElse(null);

        if (user == null || post == null) {
            return null;
        }

        comment.setUser(user);
        comment.setPost(post);

        return commentRepository.save(comment);
    }

    public boolean deleteComment(Long id) {

        if (!commentRepository.existsById(id)) {
            return false;
        }

        commentRepository.deleteById(id);
        return true;
    }
}