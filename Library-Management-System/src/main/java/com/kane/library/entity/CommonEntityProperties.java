package com.kane.library.entity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@MappedSuperclass
public class CommonEntityProperties {
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "created_ts", nullable = false)
	private LocalDateTime createdTimeStamp;

	@Column(name = "updated_ts")
	private LocalDateTime updatedTimeStamp;

	@PreUpdate
	public void updateTimeStamps() {
		updatedTimeStamp = LocalDateTime.now(ZoneOffset.UTC);
	}

	@PrePersist
	@Column(updatable = false)
	public void createTimeStamps() {
		createdTimeStamp = LocalDateTime.now(ZoneOffset.UTC);
	}

}
