package com.kane.library.request;

import lombok.Data;

@Data
public class BookIssueRequest {
	
	private Integer userId;
	private Integer bookId;
}
