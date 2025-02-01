package com.kane.library.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kane.library.entity.Books;
import com.kane.library.quriesConstants.QueryConstants;

@Repository
public interface BookRepositories extends JpaRepository<Books, Integer> {
	
	@Query(value = QueryConstants.GET_BOOK_BY_USER_AND_ID, nativeQuery = true)
	Optional<Books> getBookGyUserAndId(@Param(value = "userId") Integer userId,@Param(value="bookId") Integer bookId);
	
	@Query(value = QueryConstants.SEARCH_BY_BOOK_NAME, nativeQuery = true)
	Page<Books> searchByName(@Param(value="keyword")String keyword,Pageable p);
	
	@Query(value = QueryConstants.GET_BOOK_BY_ID_AND_STATUS, nativeQuery = true)
	Optional<Books> bookByIdAndStatus(@Param(value="id")Integer id);

}
