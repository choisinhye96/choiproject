package com.sparta.blog.repository;

import com.sparta.blog.entity.PostLike;
import com.sparta.blog.entity.Post;
import com.sparta.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByUserAndPost(User user, Post post); //JpaRepository에서 기본적으로 제공하는 메소드
    Optional<PostLike> findByUserAndPost(User user, Post post);
}
