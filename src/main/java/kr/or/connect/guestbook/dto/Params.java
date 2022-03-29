package kr.or.connect.guestbook.dto;

public class Params {
	private int start;
	private int limit;
	private String guestbookLocation;
	
	public Params(int start, int limit, String guestbookLocation) {
		super();
		this.start = start;
		this.limit = limit;
		this.guestbookLocation = guestbookLocation;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getGuestbookLocation() {
		return guestbookLocation;
	}

	public void setGuestbookLocation(String guestbookLocation) {
		this.guestbookLocation = guestbookLocation;
	}

	@Override
	public String toString() {
		return "Params [start=" + start + ", limit=" + limit + ", guestbookLocation=" + guestbookLocation + "]";
	}
	
	
	
	
}
