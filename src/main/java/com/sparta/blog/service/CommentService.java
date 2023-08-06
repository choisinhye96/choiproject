package com.sparta.blog.service;

import com.sparta.blog.dto.CommentRequestDto;
import com.sparta.blog.dto.CommentResponseDto;
import com.sparta.blog.entity.Comment;
import com.sparta.blog.entity.User;

public interface CommentService {

    /**
     * 댓글 생성
     * @param requestDto 댓글 생성 요청정보
     * @param user 댓글 생성 요청자
     * @return 생성된 댓글 정보
     */
    CommentResponseDto createComment(CommentRequestDto requestDto, User user);

    /**
     * 댓글 수정
     * @param comment 수정할 댓글
     * @param requestDto 수정할 댓글 정보
     * @param user 댓글 수정 요청자
     * @return 수정된 댓글 정보
     */
    CommentResponseDto updateComment(Comment comment, CommentRequestDto requestDto, User user);

    /**
     * 댓글 삭제
     * @param comment 삭제할 댓글
     * @param user 댓글 삭제 요청자
     */
    void deleteComment(Comment comment, User user);

    /**
     * 댓글 좋아요
     * @param id 좋아요 요청 댓글 ID
     * @param user 댓글 좋아요 요청자
     */
    void likeComment(Long id, User user);

    /**
     * 댓글 좋아요 취소
     * @param id 좋아요 취소 요청 댓글 ID
     * @param user 댓글 좋아요 취소 요청자
     */
    void deleteLikeComment(Long id, User user);

    /**
     * 댓글 Entity 단건 조회
     * @param id 조회할 댓글 ID
     * @return 댓글 Entity
     */
    Comment findComment(long id);
}
