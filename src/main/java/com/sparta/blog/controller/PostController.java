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

    @GetMapping("/posts") //글 조회
    public List<PostResponseDto> getPosts() {
        return postService.getPost();
    }

    @PutMapping("/posts/{id}") //글 수정
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    @DeleteMapping("/posts/{id}") //글 삭제
    public Long deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

//    @GetMapping("/posts/check/{id}/{password}")
//    public boolean checkPassword(@PathVariable Long id,@PathVariable String password) {
//        return postService.checkPassword(id, password);
//    }
}
