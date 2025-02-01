package com.kane.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Books")
public class Books extends CommonEntityProperties {
	
	@Column(name = "Book_Name")
	private String bookName;
	
	@Column(name = "Author_Name")
	private String authorName;
	
	@Column(name = "Price")
	private Integer price;
}
