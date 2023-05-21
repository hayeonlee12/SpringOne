package com.codingbox.sprip.inquiry;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QnARepository {
	private final EntityManager em;
	
	public void save(QnA qna) {
		em.persist(qna);
	}
}
