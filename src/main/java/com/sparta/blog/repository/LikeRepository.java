package com.sparta.blog.repository;

import com.sparta.blog.entity.Like;
import com.sparta.blog.entity.Post;
import com.sparta.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByPostAndUser(Post post, User user);
}
