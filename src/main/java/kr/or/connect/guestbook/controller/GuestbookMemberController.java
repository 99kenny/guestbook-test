package kr.or.connect.guestbook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import kr.oe.connect.guestbook.service.MemberService;
import kr.or.connect.guestbook.dao.MemberDao;
import kr.or.connect.guestbook.dto.Member;

@Controller
public class GuestbookMemberController {
	@Autowired
	MemberService memberService;
	@GetMapping(path="/login")
	public String toLonginPage(HttpSession session) {
		if(session.getAttribute("id") != null) {
			System.out.println(session.getAttribute("id"));
			return "redirect:list";
		}
		else {
			return "login";
		}
	}
	
	@PostMapping(path="/login")
	public String login(@RequestParam(value = "id") String id, @RequestParam(value = "password") String password, HttpSession session) {
	
		if(memberService.loginById(id, password).isEmpty()) {
			return "redirect:login";
		}
		
		else {
			Member member = memberService.loginById(id, password).get();
			session.setAttribute("name", member.getName());
			session.setAttribute("id", id);
		}
	
		return "redirect:list";
	}
	
	@GetMapping(path="/signup")
	public String toSignupPage() {
		return "signup";
	}
	
	@PostMapping(path="/signup")
	public String signup(@ModelAttribute Member member) {
		memberService.addMember(member);
		return "redirect:login";
	}
}
