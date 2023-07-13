package com.sparta.blog.entity;

import com.sparta.blog.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Table(name = "blog") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor //기본 생성자를 만들어줌
public class Post extends TimeStamped {
    //글 고유 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //글 제목
    @Column(nullable = false)
    private String title;
    //글 내용
    @Column(nullable = false)
    private String content;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
    //연관관계 주인 표시
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE) //게시글이 삭제되면 댓글도 같이 삭제 된다.
    private List<Comment> comments;

    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }
}