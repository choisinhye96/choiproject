package com.sparta.blog.dto;

import com.sparta.blog.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class PostResponseDto extends ApiResponseDto{
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String username;
    private List<CommentResponseDto> comments;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.username = post.getUser().getUsername();
        this.comments = post.getComments().stream().map(CommentResponseDto::new).sorted(Comparator.comparing(CommentResponseDto::getCreatedAt).reversed()).toList();
        // Comparator 비교연산자를 만들어 주는 클래스. comparing(어떤 필드로 정렬할 지) 정렬. 작성날짜(기본은 오름차순) 내림차순.
    }
}

