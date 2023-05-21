package com.codingbox.sprip.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
	
	@NotEmpty(message = "아이디를 입력하세요", groups = ValidationGroup.NotNullGroup.class)
	@Size(min = 5, max = 16, message = "최소 5자, 최대 16자로 입력해 주세요", groups = ValidationGroup.SizeCheckGroup.class)
	private String userid;
	
	@NotEmpty(message = "이를을 입력하세요", groups = ValidationGroup.NotNullGroup.class)
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣]*$", message = "한글로만 입력하세요", groups = ValidationGroup.PatternCheckGroup.class)					// 한글 벨리데이션
	private String username;
	
	@NotEmpty(message = "비밀번호를 입력하세요", groups = ValidationGroup.NotNullGroup.class)
	private String userpw;
	
	@NotEmpty(message = "이메일을 입력하세요", groups = ValidationGroup.NotNullGroup.class)
	@Email(message = "이메일 형식이 아닙니다", groups = ValidationGroup.EmailCheckGroup.class)  				// 이메일형식 벨리데이션 
	private String useremail;
	
	@NotEmpty(message = "휴대폰번호를 입력하세요", groups = ValidationGroup.NotNullGroup.class)
	@Pattern(regexp = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$",
			 message = "휴대폰번호 형식이 아닙니다.", groups = ValidationGroup.PatternCheckGroup.class) 			// - 없는 폰번호형식 벨리데이션
	private String userphone;
	
// 	MemberForm 객체를 만든 이유
//  Member 객체 자체로 입력받지 않고 따로 입력용 객체를 만들어 입력받았을 때의 장점
//	MemberForm 객체에는 Member 객체의 모든 변수가 있을 필요 없이 원하는 값만 받을 수 있음
//	오직 이 페이지에서 입력을 받기 위한 객체이므로 더 직관적임
//	다른 페이지에서도 Member을 생성할 수 있는데 이런 경우와 구분 가능
//	MemberForm에 valid 어노테이션 들을 넣어줌으로써 이 페이지의 들어오는 입력에 대한 validation만을 따로 지정할 수 있음

	
	
	
	
}
