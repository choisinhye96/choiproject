package com.sparta.blog.entity;

import com.sparta.blog.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Table(name = "blog") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor //기본 생성자를 만들어줌
public class Post extends Timestamped{
    //글 고유 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //글 제목
    @Column(name = "title", nullable = false)
    private String title;
    //글 내용
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;


    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setUser(User user) {
        this.user = user;
    }
}