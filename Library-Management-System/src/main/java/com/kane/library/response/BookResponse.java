package com.kane.library.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponse {
	
	private String bookName;
	private Integer price;
	private String author;
	private String status;

}
