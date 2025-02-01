package com.kane.library.quriesConstants;

public class QueryConstants {
	
	public static final String GET_BOOK_BY_USER_AND_ID = "select * from books b where b.id=:bookId and b.user_id=:userId";

}
