package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //ORDINAL 쓰면 숫자로 저장되는데 중간에 상태값이 추가되면 값 변경되기 때문에 항상 STRING 사용
    private DeliveryStatus status; //READY(배송준비), COMP(배송)

}
