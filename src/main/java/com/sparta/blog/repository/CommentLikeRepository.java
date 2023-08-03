package com.sparta.blog.repository;

import com.sparta.blog.entity.Comment;
import com.sparta.blog.entity.CommentLike;
import com.sparta.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    boolean existsByUserAndComment(User user, Comment comment);
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}
