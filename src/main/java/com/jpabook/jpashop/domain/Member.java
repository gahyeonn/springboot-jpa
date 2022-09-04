package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded //내장 타입을 포함했다는 의미 (@Embedded(Member) or @Embeddable(Address) 둘 중 하나만 사용 가능)
    private Address address;

    @OneToMany(mappedBy = "member") //order 테이블의 member 필드에 의해 mapping 됨 => 값 넣는다고 fk 값 변경 안됨
    private List<Order> orders = new ArrayList<>();
}
