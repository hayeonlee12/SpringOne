package com.codingbox.sprip.inquiry;

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
	
	@GetMapping("/qna/service")
	public String serviceQna(Model model) {
		return "qna/service";
	}
	
	@GetMapping("/qna/new")
	public String createQna(Model model) {
		model.addAttribute("qnaForm", new QnaForm());
		return "qna/service_inquiry";
	}
	
	@PostMapping("/qna/new")
	public String createQna(@Valid QnaForm form) {
		QnA qna = new QnA();
		qna.setQnatitle(form.getQnatitle());
		qna.setQnadetail(form.getQnadetail());
		
		qnAService.saveQna(qna);
		
		return "redirect:/qna/qnalist";
	}
}
