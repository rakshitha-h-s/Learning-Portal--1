package com.effigo.LearningPortal.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCourseEnrollmentEntity {
	@Id
	private Long enrollment_id;
	@ManyToOne
	@JoinColumn(name = "u_id",referencedColumnName="u_id")
	private UserEntity u_id;
	@ManyToOne
	@JoinColumn(name = "course_id",referencedColumnName="course_id")
	private CourseEntity course_id;
	@Column(nullable=false,unique=true)
	private String enrollment_date;
    @CreationTimestamp
    @Column(name = "created_on", nullable = false, updatable = false)
    private LocalDateTime createdOn;
    @UpdateTimestamp
    @Column(name = "updated_on", nullable = false)
    private LocalDateTime updatedOn;
	public String getEnrollment_date() {
		return enrollment_date;
	}

	public void setEnrollment_date(String enrollment_date) {
		this.enrollment_date = enrollment_date;
	}

	public Long getEnrollment_id() {
		return enrollment_id;
	}
	public void setEnrollment_id(Long enrollment_id) {
		this.enrollment_id = enrollment_id;
	}
	public UserEntity getU_id() {
		return u_id;
	}
	public void setU_id(UserEntity u_id) {
		this.u_id = u_id;
	}
	public CourseEntity getCourse_id() {
		return course_id;
	}
	public void setCourse_id(CourseEntity course_id) {
		this.course_id = course_id;
	}
	@PrePersist
    public void onCreate() {
        this.createdOn = LocalDateTime.now();
    }
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
	
}

