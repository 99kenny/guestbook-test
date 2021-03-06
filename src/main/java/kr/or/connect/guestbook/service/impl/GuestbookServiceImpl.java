package kr.or.connect.guestbook.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.guestbook.dao.GuestbookDao;
import kr.or.connect.guestbook.dao.LogDao;
import kr.or.connect.guestbook.dto.Guestbook;
import kr.or.connect.guestbook.dto.Log;
import kr.or.connect.guestbook.dto.Params;
import kr.or.connect.guestbook.service.GuestbookService;

@Service
public class GuestbookServiceImpl implements GuestbookService{
	
	@Autowired
	GuestbookDao guestbookDao;
	
	@Autowired
	LogDao logDao;
	
	@Override
	@Transactional  //읽기 만 하는 메소드는 transactional -> read only 형태로 connection 사용
	public List<Guestbook> getGuestbooks(int start, String user) {
		Params params = new Params(start, GuestbookService.LIMIT, user);
		List<Guestbook> list = guestbookDao.selectAll(params);
		for(int i =0; i < list.size(); i++) {
			System.out.println(list);
		}
		return list;
	}
	
	@Override
	@Transactional(readOnly=false)
	public int deleteGuestbook(Long id, String ip) {
		int deleteCount = guestbookDao.deleteById(id);
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("delete");
		log.setRegdate(new Date());
		logDao.insert(log);
		return deleteCount;
	}
	
	@Override
	@Transactional(readOnly=false)
	public Guestbook addGuestbook(Guestbook guestbook, String ip) {
		guestbook.setRegdate(new Date());
		Long id = guestbookDao.insert(guestbook);
		guestbook.setId(id);
		
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("insert");
		log.setRegdate(new Date());
		logDao.insert(log);
		return guestbook;
	}
	
	@Override
	public int getCount() {
		return guestbookDao.selectCount();
	}
}
