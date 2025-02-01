package com.kane.library.response;

import lombok.Data;

@Data
public class BookResponse {
	
	private String bookName;
	private Integer price;
	private String author;
	private String status;
	private UserResponse user;

}
