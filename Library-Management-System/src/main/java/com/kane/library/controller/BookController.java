package com.kane.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kane.library.request.BookIssueRequest;
import com.kane.library.request.BookRequest;
import com.kane.library.response.BookIssueResponse;
import com.kane.library.response.BookResponse;
import com.kane.library.response.BookReturnResponse;
import com.kane.library.services.BookServices;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	private BookServices bookServices;
	
	@PostMapping("/create-book")
	public BookResponse createBooks(@RequestBody BookRequest bookRequest) {
		
		return bookServices.createBook(bookRequest);
	}
	
	@PutMapping("/issue-book")
	public BookIssueResponse issueBook(@RequestBody BookIssueRequest issueRequest) {
		return bookServices.issueBook(issueRequest);
	}
	
	@PutMapping("/return-book")
	public BookReturnResponse returnBook(@RequestBody BookIssueRequest returnRequest) {
		return bookServices.returnedBook(returnRequest);
	}
	

}
