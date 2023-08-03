package com.sparta.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String body;

    @ManyToOne //여러개의 댓글을 한 게시글에 작성할 수 있음
    @JoinColumn(name = "post_id") //fk
    private Post post; //댓글은 게시글과 연관관계를 가지고 있음

    @ManyToOne //여러개의 댓글을 한 유저가 작성할 수 있음
    @JoinColumn(name = "user_id")//fk
    private User user;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<CommentLike> commentLikes = new ArrayList<>();

    public Comment(String body) { //생성자로 생성할 때 댓글 본문만 생성
        this.body = body;
    }
    public void setBody(String body) { //댓글 수정
        this.body = body;
    }

    public void setUser(User user) { //연관 관계 맵핑
        this.user = user;
    }

    public void setPost(Post post) { //연관 관계 맵핑
        this.post = post;
    }
}
