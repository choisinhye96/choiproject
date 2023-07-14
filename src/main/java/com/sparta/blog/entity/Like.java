package com.sparta.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "like")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false) //fk
    private Post post; //좋아요는 게시글과 연관관계를 가지고 있음

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)//fk
    @OnDelete(action = OnDeleteAction.CASCADE) //유저가 지워지면, 사용자 - 게시글의 연관관계인 Like도 삭제
    private User user;//여러개의 좋아요를 한 유저가 행할 수 있음

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment; //댓글에도 좋아요 누를 수 있음

    @Column(nullable = false)
    private boolean status; // true = 좋아요, false = 좋아요 취소


    public Like(Post post, User user, Comment comment){
        this.post = post;
        this.user = user;
        this.comment = comment;
        this.status = true;
    }

    public void setLiked(Like liked){
        this.status = true;
    }

//    public void unLike(boolean status){
//        this.status = false;
//        post.setLiked(post.getLike() - 1);
//    } 푸쉬 하기위해 주석 처리 ..아직 하고 있는 중입니다
}
