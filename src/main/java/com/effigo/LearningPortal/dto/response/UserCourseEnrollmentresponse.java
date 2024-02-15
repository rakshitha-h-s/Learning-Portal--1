package com.effigo.LearningPortal.dto.response;

import java.io.Serializable;

import com.effigo.LearningPortal.entity.CourseEntity;
import com.effigo.LearningPortal.entity.UserEntity;

import lombok.Data;
@Data
public class UserCourseEnrollmentresponse implements Serializable {
	private Long enrollment_id;
	private UserEntity u_id;
	private CourseEntity course_id;
	private String enrollment_date;
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
	public String getEnrollment_date() {
		return enrollment_date;
	}
	public UserCourseEnrollmentresponse() {
		super();
	}
	public void setEnrollment_date(String enrollment_date) {
		this.enrollment_date = enrollment_date;
	}

}
