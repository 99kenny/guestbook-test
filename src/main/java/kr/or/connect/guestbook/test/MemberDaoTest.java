package kr.or.connect.guestbook.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.connect.guestbook.dao.MemberDao;

public class MemberDaoTest {

	@Test
	public void test() {
		
		@Autowired
		MemberDao memberDao;
	}

}
