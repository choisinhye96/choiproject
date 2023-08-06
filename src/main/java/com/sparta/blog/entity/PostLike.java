package com.sparta.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "like")
public class PostLike extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id") //fk
    private Post post; // 양방향 연관관계

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")//fk
    private User user; //여러개의 좋아요를 한 유저가 행할 수 있음. 단방향

    public PostLike(Post post, User user){
        this.post = post;
        this.user = user;
    }
}