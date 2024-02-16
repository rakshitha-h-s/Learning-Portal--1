package com.effigo.LearningPortal.service;

import java.util.List;
import java.util.Optional;
import com.effigo.LearningPortal.dto.request.UserCourseEnrollmentrequest;
import com.effigo.LearningPortal.dto.response.UserCourseEnrollmentresponse;
import com.effigo.LearningPortal.entity.UserCourseEnrollmentEntity;

public interface UserCourseEnrollmentService {
	List<UserCourseEnrollmentEntity> findAllCourseEnrollment();
	Optional<UserCourseEnrollmentEntity> findById(Long id);
	void deleteUserCourseEnrollmententity(Long id);
	UserCourseEnrollmentresponse saveUserCourseEnrollmentEntity(UserCourseEnrollmentrequest usercourseentityrequest);
	UserCourseEnrollmentresponse updateUserCourseEnrollmentEntity(UserCourseEnrollmentrequest usercourseentityrequest,Long id);
}
