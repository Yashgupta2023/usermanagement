package com.yash.usermanagement.service;

import com.yash.usermanagement.entity.Post;
import com.yash.usermanagement.entity.User;
import com.yash.usermanagement.repository.PostRepository;
import com.yash.usermanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void testGetAllPosts() {

        Post p1 = new Post();
        p1.setTitle("Java");

        Post p2 = new Post();
        p2.setTitle("Spring");

        when(postRepository.findAll())
                .thenReturn(List.of(p1, p2));

        List<Post> posts = postService.getAllPosts();

        assertEquals(2, posts.size());

        verify(postRepository).findAll();
    }

    @Test
    void testGetPostById() {

        Post post = new Post();
        post.setId(1L);

        when(postRepository.findById(1L))
                .thenReturn(Optional.of(post));

        Optional<Post> result = postService.getPostById(1L);

        assertTrue(result.isPresent());

        verify(postRepository).findById(1L);
    }

    @Test
    void testCreatePost() {

        Post post = new Post();
        post.setTitle("JUnit");

        when(postRepository.save(any(Post.class)))
                .thenReturn(post);

        Post saved = postService.createPost(post);

        assertEquals("JUnit", saved.getTitle());

        verify(postRepository).save(post);
    }

    @Test
    void testCreatePostForUser() {

        User user = new User();
        user.setId(1L);

        Post post = new Post();
        post.setTitle("Mockito");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(postRepository.save(any(Post.class)))
                .thenReturn(post);

        Post result = postService.createPostForUser(1L, post);

        assertNotNull(result);

        verify(postRepository).save(post);
    }

    @Test
    void testUpdatePost() {

        Post existing = new Post();
        existing.setId(1L);

        Post updated = new Post();
        updated.setTitle("Updated");
        updated.setContent("Updated Content");

        when(postRepository.findById(1L))
                .thenReturn(Optional.of(existing));

        when(postRepository.save(any(Post.class)))
                .thenReturn(existing);

        Post result = postService.updatePost(1L, updated);

        assertEquals("Updated", result.getTitle());

        verify(postRepository).save(existing);
    }

    @Test
    void testDeletePost() {

        when(postRepository.existsById(1L))
                .thenReturn(true);

        boolean deleted = postService.deletePost(1L);

        assertTrue(deleted);

        verify(postRepository).deleteById(1L);
    }

    @Test
    void testSearchPosts() {

        when(postRepository.findPostsByKeyword("Java"))
                .thenReturn(List.of(new Post()));

        assertEquals(1,
                postService.searchPosts("Java").size());
    }

    @Test
    void testPostsByUser() {

        when(postRepository.findPostsByUserId(1L))
                .thenReturn(List.of(new Post()));

        assertEquals(1,
                postService.getPostsByUser(1L).size());
    }

    @Test
    void testNativeSearch() {

        when(postRepository.findPostsByKeywordNative("Spring"))
                .thenReturn(List.of(new Post()));

        assertEquals(1,
                postService.searchPostsNative("Spring").size());
    }

    @Test
    void testRecentPosts() {

        when(postRepository.findPostsCreatedAfter(any(LocalDateTime.class)))
                .thenReturn(List.of(new Post()));

        assertEquals(1,
                postService.getPostsCreatedLast7Days().size());
    }

    @Test
    void testPagination() {

        when(postRepository.findAll(PageRequest.of(0,5)))
                .thenReturn(new PageImpl<>(List.of(new Post())));

        assertEquals(
                1,
                postService.getPosts(PageRequest.of(0,5))
                        .getContent()
                        .size()
        );
    }
}