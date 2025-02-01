package com.kane.library.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kane.library.entity.Books;
import com.kane.library.entity.User;
import com.kane.library.enums.BookStatus;
import com.kane.library.repository.BookRepositories;
import com.kane.library.repository.UserRepositories;
import com.kane.library.request.BookIssueRequest;
import com.kane.library.request.BookRequest;
import com.kane.library.response.BookIssueResponse;
import com.kane.library.response.BookResponse;
import com.kane.library.response.BookReturnResponse;
import com.kane.library.services.BookServices;

@Service
public class BookServicesImpl implements BookServices {

	@Autowired
	private BookRepositories bookRepositories;
	
	@Autowired
	private UserRepositories userRepositories;
	
	@Override
	public BookResponse createBook(BookRequest bookRequest) {
		Books books = buildBooks(bookRequest);
		Books savedBooks = bookRepositories.save(books);
		return builBookResponse(savedBooks);
	}

	private BookResponse builBookResponse(Books savedBooks) {
		return BookResponse.builder().author(savedBooks.getAuthorName())
				.price(savedBooks.getPrice())
				.bookName(savedBooks.getBookName()).build();
	}

	private Books buildBooks(BookRequest bookRequest) {
		return Books.builder()
				.bookName(bookRequest.getBookName())
				.price(bookRequest.getPrice())
				.authorName(bookRequest.getAuthorName())
				.build();
	}

	@Override
	public BookIssueResponse issueBook(BookIssueRequest issueRequest) {
		// TODO Auto-generated method stub
		Optional<Books> book = bookRepositories.findById(issueRequest.getBookId());
		Optional<User> user = userRepositories.findById(issueRequest.getUserId());
		Books updateBook = book.get();
		updateBook.setStatus(BookStatus.ISSUED.toString());
		updateBook.setUsers(user.get());
		bookRepositories.save(updateBook);
		return bookIssueResponseBuilder(updateBook);
	}

	private BookIssueResponse bookIssueResponseBuilder(Books updateBook) {
		// TODO Auto-generated method stub
		return BookIssueResponse.builder()
				.userId(updateBook.getUsers().getId())
				.bookId(updateBook.getId())
				.bookStatus(updateBook.getStatus()).build();
		
	}

	@Override
	public BookReturnResponse returnedBook(BookIssueRequest returnRequest) {
		// TODO Auto-generated method stub
		Optional<Books> getBook = bookRepositories.getBookGyUserAndId(returnRequest.getUserId(), returnRequest.getBookId());
		Books returnBook= getBook.get();
		returnBook.setStatus(BookStatus.RETURNED.toString());
		returnBook.setUsers(null);
		Books updatedBook = bookRepositories.save(returnBook);
		return bookReturnResponseBuilder(updatedBook);
	}

	private BookReturnResponse bookReturnResponseBuilder(Books updatedBook) {
		// TODO Auto-generated method stub
		return BookReturnResponse.builder()
				.bookId(updatedBook.getId())
				.bookStatus(updatedBook.getStatus())
				.build();
	}

}
