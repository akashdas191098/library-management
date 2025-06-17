package com.kane.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> createBooks(@RequestBody BookRequest bookRequest) {

		String message = bookServices.createBook(bookRequest);

		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	/*
	 * Question no 1 : issue a book
	 */
	@PutMapping("/issue-book")
	public ResponseEntity<BookIssueResponse> issueBook(@RequestBody BookIssueRequest issueRequest) {

		BookIssueResponse bookIssueResponse = bookServices.issueBook(issueRequest);
		return new ResponseEntity<BookIssueResponse>(bookIssueResponse,HttpStatus.CREATED);
	}
	
	/*
	 * Question no 2 : return the book
	 */
	@PutMapping("/return-book")
	public ResponseEntity<BookReturnResponse> returnBook(@RequestBody BookIssueRequest returnRequest) {

		BookReturnResponse bookReturnResponse = bookServices.returnBook(returnRequest);
		return new ResponseEntity<BookReturnResponse>(bookReturnResponse,HttpStatus.OK);
	}
	
	/*
	 * Question no 3 & 4 show list of books and search for a specific book with some specific tileformat
	 */
	@GetMapping("/get-books")
	public ResponseEntity<BookResponseWithHeader> getBookInfos(@RequestParam(value="keyword", defaultValue = "NULL", required = false) String keyword,
			@RequestHeader(value = "pageNumber", defaultValue = ApplicationConstants.USER_SEARCH_DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestHeader(value = "pageSize", defaultValue = ApplicationConstants.USER_SEARCH_DEFAULT_PAGE_SIZE, required = false) Integer pageSize) {

		BookResponseWithHeader bookResponseWithHeader = bookServices.getBookResponse(keyword, pageNumber, pageSize);
		
		return new ResponseEntity<BookResponseWithHeader>(bookResponseWithHeader,HttpStatus.OK);
		
	}
	
	
	/*
	 * Question no 4 - Search a book with mention bookId
	 */
	@GetMapping("/get-book/{id}")
	public ResponseEntity<BookResponse> findBookById(@PathVariable("id") Integer id) {

		BookResponse bookResponse = bookServices.getBookById(id);

		return new ResponseEntity<BookResponse>(bookResponse,HttpStatus.OK);
	}
	

}
