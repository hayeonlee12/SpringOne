package com.codingbox.sprip.inquiry;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QnARepository {
	
	private final EntityManager em;
	
	// 문의 등록
	public void save(QnA qna) {
		em.persist(qna);
	}

	// 문의 조회를 아이디로 조회
	public List<QnA> findAllById() {
		return em.createQuery("select q from qna q where q.userid = userid", QnA.class)
				.getResultList();
	}
}
