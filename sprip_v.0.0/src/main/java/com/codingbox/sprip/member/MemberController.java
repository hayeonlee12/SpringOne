package com.codingbox.sprip.member;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	// 회원가입

		@GetMapping("/members/login")
		public String loginForm(Model model){
			return "/members/login";	
		}
	
	// 회원가입

	@GetMapping("/members/signup")
	public String registerForm(Model model){
		model.addAttribute("memberForm", new MemberForm());
	return "/members/signup";	
	}

	// 회원 가입
	@PostMapping("/members/signup")
	public String register(@Validated(ValidationSequence.class) MemberForm form, BindingResult result, Model model)throws IllegalAccessException {
		
		if (result.hasErrors()) {
			// 실패시 입력 데이터 값 유지
			model.addAttribute("memberForm", form);
			
			
			return "members/signup";
		}
		
		Member member = new Member();
		member.setUserid(form.getUserid());
		member.setUsername(form.getUsername());
		member.setUserpw(form.getUserpw());
		member.setUserphone(form.getUserphone());
		member.setUseremail(form.getUseremail());
		
		memberService.register(member);
	return "redirect:/members/signup";	// 아직 보낼 페이지가 없어서 본인 페이지로 넘어가게함
	}
	
	
    @ResponseBody // 값 변환을 위해 꼭 필요함
	@GetMapping("/members/idcheck") // 아이디 중복확인을 위한 값으로 따로 매핑
	public int overlappedID(Member Member) throws Exception{
		int result = memberService.overlappedID(Member); // 중복확인한 값을 int로 받음
		System.out.println("controller" + result);

		return result;
	}
	
	
	// 로그아웃
}
