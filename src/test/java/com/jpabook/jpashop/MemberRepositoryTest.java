package com.jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional //엔티티 매니저를 통한 모든 데이터 변경은 항상 트랜젝션 안에서 이루어져야 한다.
    @Rollback(false) // Transactional 어노테이션이 test case에 있으면 테스트가 끝난 다음 바로 디비 롤백하기 때문에 그것 방지
    public void testMember() {
        Member member = new Member();
        member.setUsername("memberA");

        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);  //JPA 엔티티 동일성 보장
    }
}