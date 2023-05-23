package com.codingbox.sprip.member;



import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	// 회원가입

	@GetMapping("/members/register")
	public String registerForm(Model model){
		model.addAttribute("form", new MemberForm());
	return "/members/register";	
	}

	// 회원 가입
	@PostMapping("/members/register")
	public String register(@Validated(ValidationSequence.class) MemberForm form, BindingResult result, Model model)throws IllegalAccessException {
		
		if (result.hasErrors()) {
			// 실패시 입력 데이터 값 유지
			model.addAttribute("form", form);
			
			
			return "members/register";
		}
		
		Member member = new Member();
		member.setUserid(form.getUserid());
		member.setUsername(form.getUsername());
		member.setUserpw(form.getUserpw());
		member.setUserphone(form.getUserphone());
		member.setUseremail(form.getUseremail());
		
		memberService.register(member);
	return "redirect:/members/login";	// 로그인 페이지로 넘기기
	}
	
	
    @ResponseBody // 값 변환을 위해 꼭 필요함
	@GetMapping("/members/idcheck") // 아이디 중복확인을 위한 값으로 따로 매핑
	public int overlappedID(Member Member) throws Exception{
		int result = memberService.overlappedID(Member); // 중복확인한 값을 int로 받음

		return result;
	}
	
    //회원 정보 수정
	@GetMapping("/members/{userid}/edit")
	public String editMemberForm(@PathVariable("userid") String userid, Model model) {
		Member member = memberService.findOne(userid);
		
		MemberForm form = new MemberForm();
		form.setUserid(member.getUserid());
		form.setUsername(member.getUsername());
		form.setUserpw(member.getUserpw());
		form.setUserphone(member.getUserphone());
		form.setUseremail(member.getUseremail());
		model.addAttribute("form", form);
		
		return "mypage/EditMember";
	}
	
	//회원 정보 수정
	@PostMapping("/members/{userid}/edit")
	public String editMember(@ModelAttribute("form")Member form ) {
		Member member = new Member();
		
		member.setUserid(form.getUserid());
		member.setUsername(form.getUsername());
		member.setUserpw(form.getUserpw());
		member.setUserphone(form.getUserphone());
		member.setUseremail(form.getUseremail());
		
		memberService.editMember(form.getUserid(),form);
		
		return "redirect:/members/login"; //어디로 갈지 아직 안정함
		
	}
	
	
	// 로그인 
		@GetMapping("/members/login")
		public String loginForm() {
			return "members/login";
		}
		
		@PostMapping("/members/login")
		public String login(@ModelAttribute Member member, HttpSession session) {
			Member loginResult = memberService.login(member);
			if (loginResult != null) {
				//login 성공
				session.setAttribute("loginId", loginResult.getUserid());
				return "index";
			} else {
				// 실패
				return "login";
			}
		}
//	
//	@PostMapping("/members/login")
//	public String login(@ModelAttribute("form") MemberForm form, HttpSession session, Model model) {
//		Member member = memberService.login(form);
//		
//		if (member != null) {
//			// 로그인 성공 시 세션에 회원 정보 저장
//			session.setAttribute("userid", member);
//			return "redirect:/mypage"; // 로그인 후 이동할 페이지를 설정해주세요.
//		} else {
//			// 로그인 실패 시 에러 메시지 전달
//			model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
//			return "members/login";
//		}
//	}
	// 로그아웃
	
    @PostMapping("/members/{userid}/logout")
    public String logout(@PathVariable("userid") String userid, HttpSession session) {
        // 세션에서 로그인 정보 제거
        session.invalidate();
        return "redirect:/";
    }
    
    
    //회원 탈퇴
    @PostMapping("/members/{userid}/delete")
    public String deleteMember(@PathVariable String userid, @ModelAttribute("member") Member form) {
        memberService.deleteMember(userid, form);
        return "redirect:/";
    }
    

	
}
