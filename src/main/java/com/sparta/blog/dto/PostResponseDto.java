package com.sparta.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.blog.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) //null인 아이들은 응답 제이슨에서 빼도록 해주는 어노테이션(NON_NULL만 변환!)
public class PostResponseDto {
    private Boolean success;
    private Long id;
    private String title;
    private String username;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {// 생성자1 포스트 생성이나 수정 entity -> dto
        this.id = post.getId();
        this.title = post.getTitle();
        this.username = post.getUsername();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

    public PostResponseDto(Boolean success) { //생성자2 포스트 삭제할 때 사용하는 Dto

        this.success =success;// success값만 받아 응답
    }
}

