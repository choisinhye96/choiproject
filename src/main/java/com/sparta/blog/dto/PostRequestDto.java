package com.sparta.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String content;
}