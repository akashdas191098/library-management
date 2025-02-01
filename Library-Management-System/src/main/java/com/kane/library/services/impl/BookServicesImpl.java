package com.kane.library.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kane.library.constants.ApplicationConstants;
import com.kane.library.entity.Books;
import com.kane.library.entity.User;
import com.kane.library.enums.BookStatus;
import com.kane.library.repository.BookRepositories;
import com.kane.library.repository.UserRepositories;
import com.kane.library.request.BookIssueRequest;
import com.kane.library.request.BookRequest;
import com.kane.library.response.BookIssueResponse;
import com.kane.library.response.BookResponse;
import com.kane.library.response.BookResponseWithHeader;
import com.kane.library.response.BookReturnResponse;
import com.kane.library.response.UserResponse;
import com.kane.library.services.BookServices;

@Service
public class BookServicesImpl implements BookServices {

	@Autowired
	private BookRepositories bookRepositories;
	
	@Autowired
	private UserRepositories userRepositories;
	
	@Override
	public String createBook(BookRequest bookRequest) {
		Books books = buildBooks(bookRequest);
		Books savedBooks = bookRepositories.save(books);
		return ApplicationConstants.BOOK_MSG+savedBooks.getId();
	}

//	private BookResponse builBookResponse(Books savedBooks) {
//		return BookResponse.builder().author(savedBooks.getAuthorName())
//				.price(savedBooks.getPrice())
//				.bookName(savedBooks.getBookName()).build();
//	}

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

	@Override
	public BookResponseWithHeader getBookResponse(String keyword, Integer pageNumber, Integer pageSize) {
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Books> pageBooks = null;
		if(keyword.equalsIgnoreCase("NULL")) {
			
			pageBooks = bookRepositories.findAll(p);
		}
		else {
			StringBuilder searchString = new StringBuilder();
			searchString.append(ApplicationConstants.SEARCH_FORMAT);
			searchString.append(keyword);
			searchString.append(ApplicationConstants.SEARCH_FORMAT);
			pageBooks = bookRepositories.searchByName(searchString.toString(), p);
		}
		
		List<Books> listBooks = pageBooks.getContent();
		List<BookResponse> bookResponse = listBooks.stream().map(b->buildBookResponse(b)).collect(Collectors.toList());
		
		return buildBookResponseWithHearders(bookResponse, pageBooks);
	}
	
	public BookResponse buildBookResponse(Books books) {
		BookResponse bookResponse = new BookResponse();
		bookResponse.setAuthor(books.getAuthorName());
		bookResponse.setBookName(books.getBookName());
		bookResponse.setPrice(books.getPrice());
		//bookResponse.setStatus(books.getStatus()!=null?books.getStatus():null);
		if(books.getStatus()!=null) {
			bookResponse.setStatus(books.getStatus());
			
		}
		if(books.getUsers()!=null) {
			bookResponse.setUsers(UserResponse.builder()
					.Name(books.getUsers().getFirstName()+ApplicationConstants.SPACE_FORMAT+books.getUsers().getLastName())
					.id(books.getUsers().getId()).build());
		}
		return bookResponse;
	}
	
	public BookResponseWithHeader buildBookResponseWithHearders(List<BookResponse> bookResponse, Page<Books> pagebookes) {
		return BookResponseWithHeader.builder()
				.currentPage(pagebookes.getNumber())
				.totalRecordPerPage(pagebookes.getNumberOfElements())
				.totalRecords(pagebookes.getTotalElements())
				.totalPages(pagebookes.getTotalPages())
				.books(bookResponse).build();
	}

}
