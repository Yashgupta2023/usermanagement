package com.yash.usermanagement.repository;

import com.yash.usermanagement.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
