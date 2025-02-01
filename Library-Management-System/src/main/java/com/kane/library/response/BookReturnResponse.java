package com.kane.library.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookReturnResponse {
	
	private Integer bookId;
	private String bookStatus;
}
