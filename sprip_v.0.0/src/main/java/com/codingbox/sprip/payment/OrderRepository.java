package com.codingbox.sprip.payment;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;


import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
	
	private final EntityManager em;
	
	public List<Order> findOne(Long orderid) {
		return em.createQuery("select m from Order o where m.orderid = :orderid", Order.class)
				.setParameter("orderid", orderid)
				.getResultList();
	}
	
	

}
