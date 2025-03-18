package com.kane.library.book.test.service;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kane.library.constants.ApplicationConstants;
import com.kane.library.entity.Books;
import com.kane.library.repository.BookRepositories;
import com.kane.library.request.BookRequest;
import com.kane.library.services.impl.BookServicesImpl;



@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
	
	@InjectMocks
	BookServicesImpl bookService;
	
	@Mock
	BookRepositories bookRepo;
	
	@Test
	public void testCreateBook() {
		when(bookRepo.save(createBooks())).thenReturn(createBooks());
		String result = bookService.createBook(createBookRequest());
		org.junit.Assert.assertEquals(result, ApplicationConstants.BOOK_MSG+createBooks().getId());
	}
	
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
}
