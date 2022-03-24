package kr.or.connect.guestbook.dao;

public class MemberDaoSqls {
	public static final String DELETE_BY_ID = "DELETE FROM guestbook_user WHERE id = :id";
	public static final String SELECT_COUNT = "SELECT count(*) FROM guestbook_user";
	public static final String SELECT_BY_ID = "SELECT * FROM guestbook_user WHERE id = :id AND password = :password";
}
