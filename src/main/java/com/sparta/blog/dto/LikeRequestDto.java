package com.sparta.blog.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LikeRequestDto {
    private int postLike;
    private Long postId;
    private Long userId;
    private Long commentId;
    private int commentLike;
}
