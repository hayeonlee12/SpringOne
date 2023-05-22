package com.codingbox.sprip.member;


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
		System.out.println("service" + result);
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
	
	
	
}
