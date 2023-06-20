package com.sparta.blog;

import com.sparta.blog.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransactionTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    @Rollback(value = false) // 테스트 코드에서 @Transactional 를 사용하면 테스트가 완료된 후 롤백하기 때문에 false 옵션 추가
    @DisplayName("포스트 생성 성공")
    void test1() {
        Post post = new Post();
        post.setTitle("Test");
        post.setUsername("Choi");
        post.setContents("@Transactional 테스트 중!");
        post.setPassword("1345");
        em.persist(post);  // 영속성 컨텍스트에 포스트 Entity 객체를 저장합니다.
    }
}
