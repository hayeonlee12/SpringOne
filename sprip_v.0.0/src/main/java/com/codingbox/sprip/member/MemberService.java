package com.codingbox.sprip.member;


import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	// memberRepository에 있는 로그인 / 회원가입 메소드 조회
	@Transactional
	public String register(Member member) {
		memberRepository.save(member);
		return member.getUserid();
	}
	
	// 아이디 중복체크
    public int overlappedID(Member member) throws Exception{
		int result = memberRepository.overlappedID(member);
		return result;
	}

	public Member findOne(String userid) {
		
		return memberRepository.findOne(userid);
	}
	
	// 회원정보 수정
	@Transactional
	public Member editMember(String userid, Member form) {
		Member findMember = memberRepository.findOne(userid);
		
		findMember.setUsername(form.getUsername());
		findMember.setUserpw(form.getUserpw());
		findMember.setUserphone(form.getUserphone());
		findMember.setUseremail(form.getUseremail());
		
		return findMember;
	}
	
	// 로그인
	
	public Member login(Member member) {
		Optional<MemberEntity> byUserid = memberRepository.findByUserid(member.getUserid());
		if (byUserid.isPresent()) {
			MemberEntity memberEntity = byUserid.get();
			if (memberEntity.getUserpw().equals(member.getUserpw())) {
				Member dto = Member.toMember(memberEntity);
				return dto;
			} else {
				return null;
			}
		} else {
			return null;
			
		}
		
	}
	
	
	// 로그아웃
	
	
	
	// 회원탈퇴기능
	@Transactional
	public void deleteMember(String userid, Member form) {
	    Member member = memberRepository.findOne(form.getUserid());
	    System.out.println(member);
	    if (member != null) {
	    	System.out.println("service : " + member.getUserid());
	        memberRepository.delete(member);
	    }
	}

}
