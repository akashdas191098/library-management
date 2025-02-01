package com.kane.library.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponseWithHeader {
	
    private Integer currentPage;
    private Integer totalRecordPerPage;
    private Long totalRecords;
    private Integer totalPages;
    private List<BookResponse> books;

}
