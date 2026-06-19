package com.yash.usermanagement.mapper;

import com.yash.usermanagement.dto.PostDTO;
import com.yash.usermanagement.dto.UserDTO;
import com.yash.usermanagement.entity.Post;
import com.yash.usermanagement.entity.User;

public class MapperUtil {

    public static UserDTO mapToUserDTO(User user) {

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public static PostDTO mapToPostDTO(Post post) {

        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt()
        );
    }
}