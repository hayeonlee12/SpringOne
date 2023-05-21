package com.codingbox.sprip.inquiry;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnAService {
	
	private final QnARepository qnARepository;
	@Transactional
	public void saveQna(QnA qna) {
		qnARepository.save(qna);
	}
}
