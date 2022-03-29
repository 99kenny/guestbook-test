package kr.or.connect.guestbook.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.guestbook.dto.Guestbook;
import kr.or.connect.guestbook.service.GuestbookService;

@Controller
public class GuestbookController {
	@Autowired
	GuestbookService guestbookService;
	
	public boolean isLoggedIn(HttpSession session) {
		if(session.getAttribute("id") == null) {
			System.out.println("user not logged in");
			return false;
		}
		//System.out.println("user :" + session.getAttribute("id"));
		return true;
	}
	
	@GetMapping(path="/userlist")
	public String userList(@RequestParam(name="user", required=true) String user,
			@RequestParam(name="start", required=false, defaultValue="0") Integer start, ModelMap model, HttpSession session) {
		//로그인 체크
		if(!isLoggedIn(session)) return "authority_err";
		// start로 시작하는 방명록 목록 구하기
				
				List<Guestbook> list = guestbookService.getGuestbooks(start, user);
				
				// 전체 페이지수 구하기
				int count = guestbookService.getCount();
				int pageCount = count / GuestbookService.LIMIT;
				if(count % GuestbookService.LIMIT > 0)
					pageCount++;
				
				// 페이지 수만큼 start의 값을 리스트로 저장
				// 예를 들면 페이지수가 3이면
				// 0, 5, 10 이렇게 저장된다.
				// list?start=0 , list?start=5, list?start=10 으로 링크가 걸린다.
				List<Integer> pageStartList = new ArrayList<>();
				for(int i = 0; i < pageCount; i++) {
					pageStartList.add(i * GuestbookService.LIMIT);
				}
				model.addAttribute("user",user);
				model.addAttribute("list", list);
				model.addAttribute("count", count);
				model.addAttribute("pageStartList", pageStartList);
				
		return "user_home";
	}
	
//	@GetMapping(path="/list")
//	public String list(@RequestParam(name="start", required=false, defaultValue="0") String start,
//					   ModelMap model, HttpSession session) {
//		//로그인 체크
//		if(!isLoggedIn(session)) return "authority_err";
//		String user = (String) session.getAttribute("id");
//		// start로 시작하는 방명록 목록 구하기
//		List<Guestbook> list = guestbookService.getGuestbooks(start,user);
//		
//		// 전체 페이지수 구하기
//		int limit = Integer.parseInt(GuestbookService.LIMIT);
//		int count = guestbookService.getCount();
//		int pageCount = count / limit;
//		if(count % limit > 0)
//			pageCount++;
//		
//		// 페이지 수만큼 start의 값을 리스트로 저장
//		// 예를 들면 페이지수가 3이면
//		// 0, 5, 10 이렇게 저장된다.
//		// list?start=0 , list?start=5, list?start=10 으로 링크가 걸린다.
//		List<Integer> pageStartList = new ArrayList<>();
//		for(int i = 0; i < pageCount; i++) {
//			pageStartList.add(i * limit);
//		}
//		
//		model.addAttribute("list", list);
//		model.addAttribute("count", count);
//		model.addAttribute("pageStartList", pageStartList);
//		
//		return "list";
//	}
	
	@PostMapping(path="/write")
	public String write(@RequestParam(name="user", required=true) String user, @ModelAttribute Guestbook guestbook,
						HttpServletRequest request, HttpSession session) {
		
		//로그인 체크
		if(!isLoggedIn(session)) return "authority_err";
		//빈 문자열인지 치크
		String content = guestbook.getContent().trim();
		if(content.length() == 0) {
			return "redirect:userlist?user="+user;
		}
		
		guestbook.setName((String)session.getAttribute("name"));
		guestbook.setUserId((String)session.getAttribute("id"));
		guestbook.setGuestbookLocation(user);
		String clientIp = request.getRemoteAddr();
		//System.out.println("clientIp : " + clientIp);
		guestbookService.addGuestbook(guestbook, clientIp);
		return "redirect:userlist?user="+user;
	}
}
