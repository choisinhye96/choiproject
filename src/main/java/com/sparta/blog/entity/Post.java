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
    //유저 이름
    @Column(name = "username", nullable = false)
    private String username;
    //글 내용
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;
    //비밀번호
    @Column(name = "password", nullable = false)
    private String password;

    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void checkPassword(String inputPassword) {
        if (!password.equals(inputPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}