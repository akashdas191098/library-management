package com.kane.library.book.test.service;

//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kane.library.constants.ApplicationConstants;
import com.kane.library.entity.Books;
import com.kane.library.entity.User;
import com.kane.library.repository.BookRepositories;
import com.kane.library.repository.UserRepositories;
import com.kane.library.request.BookIssueRequest;
import com.kane.library.request.BookRequest;
import com.kane.library.response.BookIssueResponse;
import com.kane.library.services.impl.BookServicesImpl;



@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
	
	@InjectMocks
	BookServicesImpl bookService;
	
	@Mock
	BookRepositories bookRepo;
	
	@Mock
	UserRepositories userRepo;
	
	@Test
	public void testCreateBook() {
		when(bookRepo.save(createBooks())).thenReturn(createBooks());
		String result = bookService.createBook(createBookRequest());
		assertEquals(result, ApplicationConstants.BOOK_MESSAGE+createBooks().getId());
	}
	
	@Test
	public void testIssueBook() {
		
		when(userRepo.findById(createBookIssueRequest().getUserId())).thenReturn(createUser());
		//when(userRepo.findById(createBookIssueRequest().getUserId())).thenThrow(new APIException(ErrorConstants.USER_NOT_FOUND+createUser().get().getId()));
		when(bookRepo.bookByIdAndStatus(createBookIssueRequest().getBookId())).thenReturn(createOptionalBooks());
		//when(bookRepo.bookByIdAndStatus(createBookIssueRequest().getBookId())).thenThrow(new APIException(ErrorConstants.BOOK_NOT_FOUND));
		when(bookRepo.save(createBooks())).thenReturn(createBooks());
		BookIssueResponse bookIssueResponse= bookService.issueBook(createBookIssueRequest());
		assertNotNull(bookIssueResponse);
		//Assert.assertEquals(bookIssueResponse, createBookIssueResponse());
	}
	
	
	private BookIssueRequest createBookIssueRequest() {
		BookIssueRequest bookIssueRequest = new BookIssueRequest();
		bookIssueRequest.setBookId(1);
		bookIssueRequest.setUserId(1);
		return bookIssueRequest;
	}
	
//	private BookIssueResponse createBookIssueResponse() {
//		return BookIssueResponse.builder().userId(Mockito.anyInt()).bookId(Mockito.anyInt()).bookStatus(Mockito.anyString()).build();
//	}
//	
	
	private BookRequest createBookRequest() {
		BookRequest bookRequest = new BookRequest();
		bookRequest.setAuthorName("test");
		bookRequest.setBookName("test");
		bookRequest.setPrice(1);
		return bookRequest;
	}
	
	private Books createBooks() {
		Books books = new Books();
		return books;
	}
	
	private Optional<User> createUser() {
		User user = new User();
		return Optional.ofNullable(user);
	}
	
	private Optional<Books> createOptionalBooks() {
		Books books = new Books();
		return Optional.ofNullable(books);
	}
}
