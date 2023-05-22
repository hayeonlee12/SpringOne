package com.codingbox.sprip.inquiry;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QnAController {
	
	private final QnAService qnAService;
	
	// 고객 센터:
	// 1. 고객 센터 첫 페이지로 자주 묻는 질문 표시
	// 2. 1:1 문의 버튼 통해 1:1 문의 등록
	@GetMapping("/qna/service")
	public String serviceQna(Model model) {
		return "qna/service";
	}
	
	// 1:1 문의 페이지 이동(service_inquiry.html)
	@GetMapping("/qna/new")
	public String createQna(Model model) {
		model.addAttribute("qnaForm", new QnaForm());
		return "qna/service_inquiry";
	}
	
	// 1:1 문의 등록:
	// 1. 1:1 문의 제목, 내용 입력 후 POST로 받고
	// 2. 마이페이지 내 문의내역 이동
	@PostMapping("/qna/new")
	public String createQna(@Valid QnaForm form) {
		QnA qna = new QnA();
		qna.setQnatitle(form.getQnatitle());
		qna.setQnadetail(form.getQnadetail());
		
		qnAService.saveQna(qna);
		
		return "redirect:/qna/ask";
	}
	
	// 1:1 문의 조회 (아이디로 조회)
	@GetMapping("/qna/ask")
	public String qnaLists(Model model) {
		List<QnA> qnas = qnAService.findQnas();
		model.addAttribute("qnas", qnas);
		return "/mypage/mypage_ask";
	}
}
