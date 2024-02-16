package com.effigo.LearningPortal.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.effigo.LearningPortal.dto.request.UserCourseEnrollmentrequest;
import com.effigo.LearningPortal.dto.response.UserCourseEnrollmentresponse;
import com.effigo.LearningPortal.entity.UserCourseEnrollmentEntity;

@Mapper
public interface UserCourseEnrollmentMapper {
	UserCourseEnrollmentMapper MAPPER =Mappers.getMapper(UserCourseEnrollmentMapper.class);
	UserCourseEnrollmentEntity fromRequestToEntity(UserCourseEnrollmentrequest usercourseRequest);
	UserCourseEnrollmentresponse fromEntityToResponse(UserCourseEnrollmentEntity usercourseEntity);
}
