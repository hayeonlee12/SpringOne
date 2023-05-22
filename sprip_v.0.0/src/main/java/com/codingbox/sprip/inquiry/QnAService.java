package com.codingbox.sprip.inquiry;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnAService {
	
	// repository 호출
	private final QnARepository qnARepository;
	
	// 문의 저장
	@Transactional
	public void saveQna(QnA qna) {
		qnARepository.save(qna);
	}
	
	// 문의들 찾아오기(아이디로)
	public List<QnA> findQnas() {
		return qnARepository.findAllById();
	}
}
