package com.sparta.blog.service;

import com.sparta.blog.dto.PostRequestDto;
import com.sparta.blog.dto.PostResponseDto;
import com.sparta.blog.entity.Post;
import com.sparta.blog.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Deprecated
    public List<PostResponseDto> getPostListV1() { //1. 리스트 반복하며 넣어주기
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for (Post post : postList) {
            postResponseDtoList.add(new PostResponseDto(post));
        }
        return postResponseDtoList;
    }

    public List<PostResponseDto> getPostListV2() { //2. Stream 형태로 변환해서 리스트로 바로 만들어주기
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPost(Long id) { //단건 조회
        Post post = findPost(id);
        return new PostResponseDto(post);
    }


    public PostResponseDto createPost(PostRequestDto requestDto) {

        // RequestDto -> Entity(게시글 생성)
        Post post = new Post(requestDto);

        // DB 저장
        Post savePost = postRepository.save(post);

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        // 해당 포스트가 DB에 존재하는지 확인
        Post post = findPost(id);
        // 비밀번호 체크
        post.checkPassword(requestDto.getPassword());
        // post 내용 수정
        post.setTitle(requestDto.getTitle());
        post.setUsername(requestDto.getUsername());
        post.setContents(requestDto.getContents());

        return new PostResponseDto(post);
    }

    public void deletePost(Long id, String password) {
        // 해당 포스트가 DB에 존재하는지 확인
        Post post = findPost(id);
        // 비밀번호 체크
        post.checkPassword(password);
        // post 삭제
        postRepository.delete(post);
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 포스트는 존재하지 않습니다.")
        );
    }
}
