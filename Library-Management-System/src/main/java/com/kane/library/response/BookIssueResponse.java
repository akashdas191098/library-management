package com.kane.library.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookIssueResponse {
	
	private Integer userId;
	private Integer bookId;
	private String bookStatus;

}
