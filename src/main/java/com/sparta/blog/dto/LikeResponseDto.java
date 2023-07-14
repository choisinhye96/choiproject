package com.sparta.blog.dto;

import com.sparta.blog.entity.Like;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeResponseDto{
    private Long postId;
    private Long userId;
    private Long commentId;

    public LikeResponseDto(Like like){
//        this.postId = like.getPost().getPostId();
//        this.userId = like.getUser().getUserId();
//        this.commentId = like.getComment().getCommentId();
    }
}
