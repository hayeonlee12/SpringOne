package com.codingbox.sprip.member;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
	
	@Autowired
	private final EntityManager em;
	
// 회원가입 메소드 
	
	public void save(Member member) {
		em.persist(member);
	}
	
	public int overlappedID(Member Member) throws Exception {
		 String userId = Member.getUserid();
		    TypedQuery<Long> query = em.createQuery("SELECT COUNT(m.userid) FROM Member m WHERE m.userid = :userid", Long.class);
		    query.setParameter("userid", userId);
		    Long count = query.getSingleResult();
		    return count.intValue();
	}

	public Member findOne(String userid) {
		
		return em.find(Member.class, userid);
	}
}
