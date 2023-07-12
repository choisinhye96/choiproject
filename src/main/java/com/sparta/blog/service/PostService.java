package com.sparta.blog.service;

import com.sparta.blog.dto.PostListResponseDto;
import com.sparta.blog.dto.PostRequestDto;
import com.sparta.blog.dto.PostResponseDto;
import com.sparta.blog.entity.Post;
import com.sparta.blog.entity.User;
import com.sparta.blog.entity.UserRoleEnum;
import com.sparta.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        // RequestDto -> Entity(게시글 생성)
        Post post = new Post(requestDto);
        post.setUser(user);
        // DB 저장
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    public PostListResponseDto getPosts(){
        List<PostResponseDto> postList = postRepository.findAll().stream().map(PostResponseDto::new).collect(Collectors.toList());
        return new PostListResponseDto(postList);
    }

    public PostResponseDto getPostById(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, User user) {
        // 해당 포스트가 DB에 존재하는지 확인
        Post post = findPost(id);
        //게시글 작성자(post.user)와 요청자(user)가 같은지 또는 Admin인지 체크(아니면 예외발생)
        if(!(user.getRole().equals(UserRoleEnum.ADMIN) || post.getUser().equals(user))) {
            throw new RejectedExecutionException();
        }
        // post 내용 수정
        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());

        return new PostResponseDto(post);
    }

    public void deletePost(Long id, User user) {
        //해당 포스트가 DB에 존재하는지 확인
        Post post = findPost(id);
        //게시글 작성자(post.user)와 요청자(user)가 같은지 또는 Admin인지 체크(아니면 예외발생)
        if(!(user.getRole().equals(UserRoleEnum.ADMIN) || post.getUser().equals(user))) {
            throw new RejectedExecutionException();
        }
        // post 삭제
        postRepository.delete(post);
    }

    public Post findPost(Long id) {// 없으면 예외 던져주기
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 포스트는 존재하지 않습니다.")
        );
    }
}
