package com.effigo.learning.portal.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "favorite_entity")
public class FavoriteEntity {
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "f_id")
	private Long fId;
	@ManyToOne
	@JoinColumn(name = "uId", referencedColumnName = "u_id")
	private UserEntity uId;
	@ManyToOne
	@JoinColumn(name = "courseId", referencedColumnName = "course_id")
	private CourseEntity courseId;
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
