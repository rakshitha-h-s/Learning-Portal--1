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
	private Long course_id;
	private String course_name;
    private CategoryEntity category;
    private UserEntity u_id;
}
