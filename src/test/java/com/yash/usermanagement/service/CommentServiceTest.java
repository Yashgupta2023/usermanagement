package com.yash.usermanagement.service;

import com.yash.usermanagement.entity.Comment;
import com.yash.usermanagement.entity.Post;
import com.yash.usermanagement.entity.User;
import com.yash.usermanagement.repository.CommentRepository;
import com.yash.usermanagement.repository.PostRepository;
import com.yash.usermanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentService commentService;

    @Test
    void testGetAllComments() {

        when(commentRepository.findAll())
                .thenReturn(List.of(new Comment()));

        assertEquals(
                1,
                commentService.getAllComments().size()
        );
    }

    @Test
    void testGetCommentById() {

        Comment comment = new Comment();

        when(commentRepository.findById(1L))
                .thenReturn(Optional.of(comment));

        assertTrue(
                commentService.getCommentById(1L).isPresent()
        );
    }

    @Test
    void testCreateComment() {

        User user = new User();
        user.setId(1L);

        Post post = new Post();
        post.setId(1L);

        Comment comment = new Comment();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(postRepository.findById(1L))
                .thenReturn(Optional.of(post));

        when(commentRepository.save(any(Comment.class)))
                .thenReturn(comment);

        Comment result =
                commentService.createComment(1L,1L,comment);

        assertNotNull(result);

        verify(commentRepository).save(comment);
    }

    @Test
    void testDeleteComment() {

        when(commentRepository.existsById(1L))
                .thenReturn(true);

        boolean deleted =
                commentService.deleteComment(1L);

        assertTrue(deleted);

        verify(commentRepository).deleteById(1L);
    }

    @Test
    void testCreateCommentInvalidUser() {

        Comment comment = new Comment();

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertNull(
                commentService.createComment(1L,1L,comment)
        );
    }

    @Test
    void testDeleteCommentNotFound() {

        when(commentRepository.existsById(1L))
                .thenReturn(false);

        assertFalse(
                commentService.deleteComment(1L)
        );
    }
}