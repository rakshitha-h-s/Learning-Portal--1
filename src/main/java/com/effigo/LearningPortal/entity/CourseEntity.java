package com.effigo.LearningPortal.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseEntity {
	@Id
	private Long courseId;
	private String courseName;
	@ManyToOne
	@JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
	private CategoryEntity category;
	@ManyToOne
	@JoinColumn(name = "uId", referencedColumnName = "uId")
	private UserEntity id;
	@CreationTimestamp
	@Column(name = "created_on", nullable = false, updatable = false)
	private LocalDateTime createdOn;
	@UpdateTimestamp
	@Column(name = "updated_on", nullable = false)
	private LocalDateTime updatedOn;

	@PrePersist
	public void onCreate() {
		this.createdOn = LocalDateTime.now();
	}

	public String getFormattedCreatedOn() {
		if (createdOn == null) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		return updatedOn.format(formatter);
	}

	public String getFormattedUpdatedOn() {
		if (updatedOn == null) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		return updatedOn.format(formatter);
	}

}
