package com.sparta.blog.controller;

import com.sparta.blog.dto.PostRequestDto;
import com.sparta.blog.dto.PostResponseDto;
import com.sparta.blog.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts") //글 등록
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @GetMapping("/posts") //글 전체 조회
    public List<PostResponseDto> getPostList() {
        return postService.getPostListV2();
    }

    @GetMapping("/posts/{id}") //글 단건 조회
    public PostResponseDto getPosts(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PutMapping("/posts/{id}") //글 수정
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    @DeleteMapping("/posts/{id}") //글 삭제
    public PostResponseDto deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        postService.deletePost(id, requestDto.getPassword());
        return new PostResponseDto(true);
    }


}
