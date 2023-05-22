package com.codingbox.sprip.payment;


import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PaymentRepository {
	
	private EntityManager em;
	
	public void save(Payment payment) {
		em.persist(payment);
	}
}
