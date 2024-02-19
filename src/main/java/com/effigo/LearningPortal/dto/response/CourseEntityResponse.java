package com.effigo.LearningPortal.dto.response;

import java.io.Serializable;

import com.effigo.LearningPortal.entity.CategoryEntity;
import com.effigo.LearningPortal.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntityResponse implements Serializable {
	private Long courseId;
	private String courseName;
	private CategoryEntity category;
	private UserEntity id;
}
