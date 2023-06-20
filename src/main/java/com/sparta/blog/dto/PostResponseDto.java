package com.sparta.blog.dto;

import com.sparta.blog.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String username;
    private String password;
    private String contents;

    public PostResponseDto(Post post) {// entity -> dto
        this.id = post.getId();
        this.title = post.getTitle();
        this.username = post.getUsername();
        this.password = post.getPassword();
        this.contents = post.getContents();
    }

    public PostResponseDto(Long id, String title, String username, String contents, String password) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.contents = contents;
        this.password = password;
    }
}

