package com.kane.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Books")
public class Books extends CommonEntityProperties {
	
	@Column(name = "Book_Name")
	private String bookName;
	
	@Column(name = "Author_Name")
	private String authorName;
	
	@Column(name = "Price")
	private Integer price;
	
	@Column(name = "Book_Status")
	private String status;
	
	@JsonIgnore
    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User users;
}
