package com.codingbox.sprip.inquiry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.codingbox.sprip.member.Member;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class QnA {
	
	@Id @GeneratedValue
	private Long qnaid;
	
	private String qnatitle;
	private String qnadetail;
	
	@ManyToOne
	@JoinColumn(name = "USERID")
	private Member member;
	
	
}
