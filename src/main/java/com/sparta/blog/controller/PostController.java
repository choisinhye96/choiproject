package com.sparta.blog.controller;

import com.sparta.blog.dto.ApiResponseDto;
import com.sparta.blog.dto.PostListResponseDto;
import com.sparta.blog.dto.PostRequestDto;
import com.sparta.blog.dto.PostResponseDto;
import com.sparta.blog.security.UserDetailsImpl;
import com.sparta.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api")

public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts") //글 등록
    public ResponseEntity<PostResponseDto> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto requestDto) {
        PostResponseDto result = postService.createPost(requestDto, userDetails.getUser());

        return ResponseEntity.status(201).body(result);
    }

    @GetMapping("/posts") //글 전체 조회
    public ResponseEntity<PostListResponseDto> getPosts() {
        PostListResponseDto result = postService.getPosts();

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/posts/{id}") //글 단건 조회
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        PostResponseDto result = postService.getPostById(id);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/posts/{id}") //글 수정
    public ResponseEntity<PostResponseDto> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        try{
            PostResponseDto result = postService.updatePost(id, requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(result);
        }catch(RejectedExecutionException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/posts/{id}") //글 삭제
    public ResponseEntity<ApiResponseDto> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        try{
            postService.deletePost(id, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("게시글 삭제 성공", HttpStatus.OK.value()));
        }catch(RejectedExecutionException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
