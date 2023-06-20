package com.sparta.blog.service;

import com.sparta.blog.dto.PostRequestDto;
import com.sparta.blog.dto.PostResponseDto;
import com.sparta.blog.entity.Post;
import com.sparta.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public PostResponseDto createPost(PostRequestDto requestDto) {

        // RequestDto -> Entity
        Post post = new Post(requestDto);

        // DB 저장
        Post savePost = postRepository.save(post);

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    public List<PostResponseDto> getPost() {
        // DB 조회
        return postRepository.findAll();
    }

    public Long updatePost(Long id, PostRequestDto requestDto) {
        // 해당 포스트가 DB에 존재하는지 확인
        Post post = postRepository.findById(id);
        if(post != null) {
            // post 내용 수정
            postRepository.update(id, requestDto);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 포스트는 존재하지 않습니다.");
        }
    }

    public Long deletePost(Long id) {// 해당 포스트가 DB에 존재하는지 확인
        Post post = postRepository.findById(id);
        if(post != null) {
            // post 삭제
            postRepository.delete(id);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 포스트는 존재하지 않습니다.");
        }
    }
}
