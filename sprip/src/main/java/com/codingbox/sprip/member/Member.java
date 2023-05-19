package com.codingbox.sprip.member;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

	// 회원 테이블, 데이터 정의
	@Id
	private String userid;
	private String username;
	private String userpw;
	private String useremail;
	private String userphone;
	
	// 외래키
//	@OneToMany(mappedBy = "member")
//	private List<Reserve> reserves = new ArrayList<>();
}
