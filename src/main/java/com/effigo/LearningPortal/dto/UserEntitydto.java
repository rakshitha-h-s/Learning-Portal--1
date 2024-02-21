package com.effigo.LearningPortal.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.effigo.LearningPortal.entity.UserEntity.UserType;

import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntitydto implements Serializable {
	private Long uId;
	private String username;

	public Long getuId() {
		return uId;
	}

	public void setuId(Long uId) {
		this.uId = uId;
	}

	private String password;
	private UserType userType;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;

	@PrePersist
	public void onCreate() {
		this.createdOn = LocalDateTime.now();
	}

	// method to get createdOn field in "MM/DD/YYYY HH:MM" format
	public String getFormattedCreatedOn() {
		if (createdOn == null) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		return createdOn.format(formatter);
	}

	public String getFormattedUpdatedOn() {
		if (updatedOn == null) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		return updatedOn.format(formatter);
	}

}
