package com.effigo.LearningPortal.service;

import java.util.List;
import java.util.Optional;
import com.effigo.LearningPortal.dto.request.CourseEntityrequest;
import com.effigo.LearningPortal.dto.response.CourseEntityResponse;
import com.effigo.LearningPortal.entity.CourseEntity;

public interface CourseService {
	List<CourseEntity> findAllCourse();
	Optional<CourseEntity> findById(Long id);
	void deleteCourseentity(Long id);
	CourseEntityResponse saveCourseEntity(CourseEntityrequest courserequest);
	CourseEntityResponse updateCourseEntity(CourseEntityrequest courserequest,Long id);
}
