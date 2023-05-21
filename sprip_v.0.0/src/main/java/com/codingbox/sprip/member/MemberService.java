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
	
    public int overlappedID(Member member) throws Exception{
		int result = memberRepository.overlappedID(member);
		System.out.println("service" + result);
		return result;
	}
	
}
