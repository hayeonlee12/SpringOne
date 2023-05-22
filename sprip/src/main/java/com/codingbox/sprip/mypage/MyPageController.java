package com.codingbox.sprip.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {
	@GetMapping("/mypage")
	public String mypage() {
		return "/mypage/mypage";
	}
	
	// 예약 내역 요청 주소 메소드
	// 항공권, 숙소 예약 내역 조회
	
	
	// 개인정보 수정 요청 주소 메소드
	// 기능: 개인정보 수정, 회원 탈퇴 (DB 데이터 삭제 처리)

	
}
