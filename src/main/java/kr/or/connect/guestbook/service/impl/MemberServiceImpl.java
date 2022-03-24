package kr.or.connect.guestbook.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.oe.connect.guestbook.service.MemberService;
import kr.or.connect.guestbook.dao.MemberDao;
import kr.or.connect.guestbook.dto.Member;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberDao memberDao;

	@Override
	@Transactional(readOnly=false)
	public Member addMember(Member member) {
		memberDao.insert(member);
		return null;
	}
	
	@Override
	@Transactional(readOnly=false)
	public int deleteMember(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional(readOnly=false)
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public Optional<Member> loginById(String id, String password) {
		return memberDao.login(id, password);
	}
	
	
}
