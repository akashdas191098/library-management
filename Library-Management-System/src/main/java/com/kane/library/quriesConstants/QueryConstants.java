package com.kane.library.quriesConstants;

public class QueryConstants {
	
	public static final String GET_BOOK_BY_USER_AND_ID = "select * from books b where b.id=:bookId and b.user_id=:userId";
	
	public static final String SEARCH_BY_BOOK_NAME = "select * from books b where b.book_name like :keyword order by b.id desc";
	
	public static final String GET_BOOK_BY_ID_AND_STATUS = "SELECT * FROM books b where b.id=:id and b.book_status='AVAILABLE'";

}
