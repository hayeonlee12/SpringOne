package com.codingbox.sprip.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class MemberEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String userid;
	private String username;
	private String userpw;
	private String useremail;
	private String userphone;
	
	public static MemberEntity toMemberEntity(Member member) {
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setUsername(member.getUsername());
		memberEntity.setUserpw(member.getUserpw());
		memberEntity.setUseremail(member.getUseremail());
		memberEntity.setUserphone(member.getUserphone());
		return memberEntity;
		
	}
}
