package com.yash.usermanagement.repository;

import com.yash.usermanagement.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    long countByPostId(Long postId);

}