package com.sparta.blog.service;

import com.sparta.blog.dto.CommentRequestDto;
import com.sparta.blog.dto.CommentResponseDto;
import com.sparta.blog.entity.Comment;
import com.sparta.blog.entity.Post;
import com.sparta.blog.entity.User;
import com.sparta.blog.entity.UserRoleEnum;
import com.sparta.blog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostService postService;
    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
       //게시글 있는지 확인
        Post post = postService.findPost(requestDto.getPostId());
        //새로운 댓글 만들기
        Comment comment = new Comment(requestDto.getBody());
        comment.setUser(user); //유저 정보 받고
        comment.setPost(post); //조회한 게시글 받고
        var savedComment = commentRepository.save(comment); //DB에 저장
        return new CommentResponseDto(savedComment); //CommentResponseDto 생성자를 통해 필드 추가
    }

    @Transactional //entity에 수정,삭제가 일어나면 메소드가 종류되는 시점에 db에 반영이 되도록 하는 역할
    //반환하기 위한 CommentResponseDto(타입 정의)
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto,User user) {
        //commentRepository 댓글 정보 조회
        Comment comment = commentRepository.findById(id).orElseThrow();
        // 요청자가 운영자이거나 댓글 작성자(post.user)와 요청자(user)가 같은지 체크
        if (!user.getRole().equals(UserRoleEnum.ADMIN) && !comment.getUser().equals(user)) { //equals 사용 시 객체id로 비교하기 때문에 같은 정보라 해도 '같다'라는 응답이 안내려옴. 그래서 User에 @EqualsAndHashCode를 입력해줘야 객체의 필드값을 기준으로 같은지 다른지 비교해줌
            throw new RejectedExecutionException();
        }
        comment.setBody(requestDto.getBody());
        return new CommentResponseDto(comment); //setBody에서 수정한 내용이 메소드 종류 직후에 바로 Transactional이 닫히면서 DB에 저장됨
    }
    public void deleteComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        // 요청자가 운영자이거나 댓글 작성자(post.user)와 요청자(user)가 같은지 체크
        if (!user.getRole().equals(UserRoleEnum.ADMIN) && !comment.getUser().equals(user)) {
            throw new RejectedExecutionException();
        }
        commentRepository.delete(comment);
    }
}
