package com.kane.library.request;

import lombok.Data;

@Data
public class BookRequest {
	
	private String bookName;
	private Integer price;
	private String authorName;
}
