package com.kane.library.services;

import com.kane.library.request.BookIssueRequest;
import com.kane.library.request.BookRequest;
import com.kane.library.response.BookIssueResponse;
import com.kane.library.response.BookResponse;
import com.kane.library.response.BookResponseWithHeader;
import com.kane.library.response.BookReturnResponse;

public interface BookServices {
	
	public String createBook(BookRequest bookRequest);
	
	public BookIssueResponse issueBook(BookIssueRequest issueRequest);
	
	public BookReturnResponse returnedBook(BookIssueRequest returnRequest);
	
	public BookResponseWithHeader getBookResponse(String keyword, Integer pageNumber, Integer pageSize);
	
	public BookResponse getBookById(Integer id);
}
