package com.sparta.blog.service;

import com.sparta.blog.dto.PostListResponseDto;
import com.sparta.blog.dto.PostRequestDto;
import com.sparta.blog.dto.PostResponseDto;
import com.sparta.blog.entity.Post;
import com.sparta.blog.entity.User;

public interface PostService {
    /*
    기존 PostService를 인터페이스로 생성하고 기존 메소드들에 더 많은 메소드가 추가되야 함으로 그렇게 될 시
    해당 클래스를 한눈에 파악하기 힘들어짐. 그래서 메소그 선언에 대한 부분을 인터페이스로 따로 빼고 구현은 임플리먼트라는
    클래스에 만들어 줌
    사용할 메드들의 선언과 주석(메소드들의 역할과 파라미터(매개변수들)의 설명, 리턴값에 대한 설명) 추가
     */

    /**
     * 게시글 생성
     * @param requestDto 게시글 생성 요청정보
     * @param user 게시글 생성 요청자
     * @return 게시글 생성 결과
     */
    PostResponseDto createPost(PostRequestDto requestDto, User user);

    /**
     * 전체 게시글 목록 조회
     * @return 전체 게시글 목록
     */
    PostListResponseDto getPosts();

    /**
     * 게시글 단건 조회
     * @param id 조회할 게시글 ID
     * @return 조회된 게시글 정보
     */
    PostResponseDto getPostById(Long id);

    /**
     * 게시글 업데이트
     * @param post 업데이트 할 게시글
     * @param requestDto 업데이트 할 게시글 정보
     * @param user 게시글 업데이트 요청자
     * @return 업데이트된 게시글 정보
     */
    PostResponseDto updatePost(Post post, PostRequestDto requestDto, User user);

    /**
     * 게시글 삭제
     * @param post 삭제 요청 게시글
     * @param user 게시글 삭제 요청자
     */
    void deletePost(Post post, User user);

    /**
     * 게시글 좋아요
     * @param id 좋아요 요청 게시글 ID
     * @param user 게시글 좋아요 요청자
     */
    void likePost(Long id, User user);

    /**
     * 게시글 좋아요 취소
     * @param id 좋아요 취소 요청 게시글 ID
     * @param user 게시글 좋아요 취소 요청자
     */
    void deleteLikePost(Long id, User user);

    /**
     * 게시글 Entity 단건 조회
     * @param id 조회할 게시글 ID
     * @return 게시글 Entity
     */
    Post findPost(long id);
}
