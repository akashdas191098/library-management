package com.kane.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kane.library.constants.ApplicationConstants;
import com.kane.library.request.BookIssueRequest;
import com.kane.library.request.BookRequest;
import com.kane.library.response.BookIssueResponse;
import com.kane.library.response.BookResponse;
import com.kane.library.response.BookResponseWithHeader;
import com.kane.library.response.BookReturnResponse;
import com.kane.library.services.BookServices;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	private BookServices bookServices;
	
	@PostMapping("/create-book")
	public String createBooks(@RequestBody BookRequest bookRequest) {
		
		return bookServices.createBook(bookRequest);
	}
	
	/*
	 * Question no 1 : issue a book
	 */
	@PutMapping("/issue-book")
	public BookIssueResponse issueBook(@RequestBody BookIssueRequest issueRequest) {
		return bookServices.issueBook(issueRequest);
	}
	
	/*
	 * Question no 2 : return the book
	 */
	@PutMapping("/return-book")
	public BookReturnResponse returnBook(@RequestBody BookIssueRequest returnRequest) {
		return bookServices.returnBook(returnRequest);
	}
	
	/*
	 * Question no 3 & 4 show list of books and search for a specific book with some specific tileformat
	 */
	@GetMapping("/get-books")
	public BookResponseWithHeader getBookInfos(@RequestParam(value="keyword", defaultValue = "NULL", required = false) String keyword,
			@RequestHeader(value = "pageNumber", defaultValue = ApplicationConstants.USER_SEARCH_DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestHeader(value = "pageSize", defaultValue = ApplicationConstants.USER_SEARCH_DEFAULT_PAGE_SIZE, required = false) Integer pageSize) {
		
		return bookServices.getBookResponse(keyword, pageNumber, pageSize);
		
	}
	
	
	/*
	 * Question no 4 - Search a book with mention bookId
	 */
	@GetMapping("/get-book/{id}")
	public BookResponse findBookById(@PathVariable("id") Integer id) {
		return bookServices.getBookById(id);
	}
	

}
