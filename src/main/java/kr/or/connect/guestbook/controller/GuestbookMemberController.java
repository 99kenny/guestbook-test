package kr.or.connect.guestbook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.guestbook.dao.MemberDao;
import kr.or.connect.guestbook.dto.Member;
import kr.or.connect.guestbook.service.MemberService;

@Controller
public class GuestbookMemberController {
	@Autowired
	MemberService memberService;
	@GetMapping(path="/login")
	public String toLonginPage(HttpSession session) {
		String id = (String) session.getAttribute("id");
		if(id != null) {
			//System.out.println(session.getAttribute("id"));
			return "redirect:userlist?user="+id;
		}
		else {
			return "login";
		}
	}
	
	@PostMapping(path="/login")
	public String login(@RequestParam(value = "id") String id, @RequestParam(value = "password") String password, HttpSession session) {
	
		if(memberService.loginById(id, password).isEmpty()) {
			return "login";
		}
		
		else {
			Member member = memberService.loginById(id, password).get();
			session.setAttribute("name", member.getName());
			session.setAttribute("id", id);
		}
	
		return "redirect:userlist?user="+id;
	}
	
	@GetMapping(path="/signup")
	public String toSignupPage() {
		return "signup";
	}
	
	@PostMapping(path="/signup")
	public String signup(@ModelAttribute Member member) {
		memberService.addMember(member);
		return "login";
	}
	
	@GetMapping(path="/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("id");
		session.removeAttribute("name");
		return "login";
	}
}
