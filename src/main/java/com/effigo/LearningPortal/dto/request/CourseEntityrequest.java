package com.effigo.LearningPortal.dto.request;

import java.io.Serializable;
import com.effigo.LearningPortal.entity.CategoryEntity;
import com.effigo.LearningPortal.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntityrequest implements Serializable {
	private Long courseId;
	private String courseName;
    private CategoryEntity category;
    private UserEntity uId;
}
