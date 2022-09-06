package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    /*
    @PersistenceContext //EntityManager 주입
    private EntityManager em;
     */

    //멤버 저장
    public void save(Member member) { //JPA가 member를 저장하는 로직
        em.persist(member);
    }

    //멤버 단건 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    //멤버 정보 조회
    public List<Member> findAll() {
        //SQL은 table을 대상으로 쿼리를 작성하는 반면, JPQL은 Entity(객체)를 대상으로 쿼리 작성
        return em.createQuery("select m from Member m", Member.class) //JPQL 작성
                .getResultList();
    }

    //회원 이름으로 정보 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
