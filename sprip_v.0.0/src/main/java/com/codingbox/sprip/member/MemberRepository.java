package com.codingbox.sprip.member;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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
	// 아이디 중복 체크
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
	
	// 로그인
	public Optional<MemberEntity> findByUserid(String userid) {
		 String query = "SELECT m FROM MemberEntity m WHERE m.userid = :userid";
	        TypedQuery<MemberEntity> typedQuery = em.createQuery(query, MemberEntity.class);
	        typedQuery.setParameter("userid", userid);

	        try {
	            MemberEntity memberEntity = typedQuery.getSingleResult();
	            return Optional.ofNullable(memberEntity);
	        } catch (NoResultException e) {
	            return Optional.empty();
	        }
	} 
	
	
	// 로그아웃
	
	
	// 회원탈퇴
	@Transactional
	public void delete(Member member) {
	    em.remove(member);
	}
}
