package com.effigo.learning.portal.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.effigo.learning.portal.entity.CourseEntity;
import com.effigo.learning.portal.entity.UserEntity;

import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteEntitydto implements Serializable {
	private Long fId;
	private UserEntity uId;
	private CourseEntity courseId;
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

	public Long getfId() {
		return fId;
	}

	public void setfId(Long fId) {
		this.fId = fId;
	}

	public UserEntity getuId() {
		return uId;
	}

	public void setuId(UserEntity uId) {
		this.uId = uId;
	}
}
