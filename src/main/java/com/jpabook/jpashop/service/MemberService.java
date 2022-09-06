package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //read시 최적화, 데이터 변경 발생 무시
@RequiredArgsConstructor //final 필드에 대한 생성자만 생성 (권장)
//@AllArgsConstructor //생성자 생성 코드 생략 가능
public class MemberService {

    private final MemberRepository memberRepository;

    /*
    //필드 인젝션
    @Autowired
    private MemberRepository memberRepository;

    //세터 인젝션
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //생성자 인젝션 => 권장 (어노테이션 생략 가능)
    @Autowired
    //@RequiredArgsConstructor, @AllArgsConstructor로 대체 가능
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    //회원 가입
    @Transactional //데이터 변경은 항상 트랜잭션 선언
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //회원 전체 조회
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName()); //디비에 name을 unique로 선언 필요
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //단일 회원 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
