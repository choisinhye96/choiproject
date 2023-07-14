package com.sparta.blog.service;

import com.sparta.blog.dto.LikeRequestDto;
import com.sparta.blog.dto.LikeResponseDto;
import com.sparta.blog.entity.Comment;
import com.sparta.blog.entity.Like;
import com.sparta.blog.entity.Post;
import com.sparta.blog.entity.User;
import com.sparta.blog.repository.CommentRepository;
import com.sparta.blog.repository.LikeRepository;
import com.sparta.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private LikeRepository likeRepository;

//    public LikeResponseDto plusOrMinuslikesToPost(Long id, LikeRequestDto requestDto, User user){
//        //게시글이 있는지 확인
//        Post post = postRepository.findById(id).orElseThrow();
//        if(likeRepository.findByPostAndUser(post, user) == null) {
//
//    }푸쉬 하기위해 주석 처리 ..아직 하고 있는 중입니다
}
