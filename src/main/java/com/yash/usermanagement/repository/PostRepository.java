package com.yash.usermanagement.repository;

import com.yash.usermanagement.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.title LIKE %?1%")
    List<Post> findPostsByKeyword(String keyword);

    @Query("SELECT p FROM Post p WHERE p.user.id = ?1")
    List<Post> findPostsByUserId(Long userId);

    @Query(
            value = "SELECT * FROM posts WHERE title LIKE %?1%",
            nativeQuery = true
    )
    List<Post> findPostsByKeywordNative(String keyword);

    @Query("SELECT p FROM Post p WHERE p.createdAt >= ?1")
    List<Post> findPostsCreatedAfter(LocalDateTime dateTime);
}