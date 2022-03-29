package kr.or.connect.guestbook.dao;

public class GuestbookDaoSqls {
	//limit -> 특정한 부분만 select
	public static final String SELECT_PAGING = "SELECT id, user_id, name, content, regdate, guestbook_location FROM guestbook WHERE guestbook_location= :guestbookLocation ORDER BY id DESC limit :start, :limit";
	public static final String SELECT = "SELECT * FROM guestbook WHERE guestbook_location = :guestbookLocation";
	public static final String DELETE_BY_ID = "DELETE FROM guestbook WHERE id = :id";
	public static final String SELECT_COUNT = "SELECT count(*) FROM guestbook";
}
