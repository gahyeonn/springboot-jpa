package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    //상품 업데이트 & 신규 등록
    public void save(Item item) {
        if (item.getId() == null) { // 신규 등록
            em.persist(item);
        } else { // 이미 등록된 아이템 가져옴
            em.merge(item);
        }
    }

    //상품 단건 조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    //상품 전체 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
