package kr.or.connect.guestbook.service;

import kr.or.connect.guestbook.dto.Member;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;


public interface MemberService {
	public Member addMember(Member member);
	public Optional<Member> loginById(String id, String password);
	public int deleteMember(String id);
	public int getCount();	
}
