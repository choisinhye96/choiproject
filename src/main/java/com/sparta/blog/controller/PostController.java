package com.sparta.blog.controller;

import com.sparta.blog.dto.PostResponseDto;
import com.sparta.blog.dto.PostRequestDto;
import com.sparta.blog.entity.Post;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostController {

    private final Map<Long, Post> postList = new HashMap<>();

    @PostMapping("/posts")
    public PostResponseDto creatPost(@RequestBody PostRequestDto requestDto) {
        // RequestDto -> Entity
        Post post = new Post(requestDto);

        // Post Max ID Check
        Long maxId = postList.size() > 0 ? Collections.max(postList.keySet()) + 1 : 1;
        post.setId(maxId);

        //DB 저장
        postList.put(post.getId(), post);

        //Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPost() {
        // Map TO List
        List<PostResponseDto> responseList = postList.values().stream().map(PostResponseDto::new).toList();

        return responseList;
    }

    @PutMapping("/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        // 해당 포스터가 DB에 존재하는지 확인
        if (postList.containsKey(id)) {
            // 해당 포스터 가져오기
            Post post = postList.get(id);

            //post 수정
            post.update(requestDto);
            return post.getId();

        } else {
            throw new IllegalArgumentException("선택하신 포스터는 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        //해당 포스트가 DB에 존재하는지 확인
        if (postList.containsKey(id)) {
            //해당 포스트 삭제하기
            postList.remove(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택하신 포스터는 존재하지 않습니다.");
        }
    }

}
