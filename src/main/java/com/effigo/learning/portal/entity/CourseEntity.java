package com.effigo.learning.portal.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "course_entity")
public class CourseEntity {
	@Id
	@GeneratedValue(generator = "courseIdGenerator")
	@GenericGenerator(name = "courseIdGenerator", strategy = "com.effigo.learning.portal.entity.CustomCourseIdGenerator")
	@Column(name = "course_id")
	private String courseId;
	@Column(name = "course_name")
	private String courseName;
	@ManyToOne
	@JoinColumn(name = "categoryId", referencedColumnName = "category_id")
	private CategoryEntity category;
	@ManyToOne
	@JoinColumn(name = "uId", referencedColumnName = "u_id")
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
