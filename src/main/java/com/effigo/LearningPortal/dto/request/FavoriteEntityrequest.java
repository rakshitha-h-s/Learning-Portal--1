package com.effigo.LearningPortal.dto.request;

import java.io.Serializable;
import com.effigo.LearningPortal.entity.CourseEntity;
import com.effigo.LearningPortal.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteEntityrequest implements Serializable {
	private Long f_id;
	private UserEntity u_id;
	private CourseEntity course_id;
}
