package com.effigo.LearningPortal.dto.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;

import com.effigo.LearningPortal.dto.UserCourseEnrollmentdto;
import com.effigo.LearningPortal.entity.UserCourseEnrollmentEntity;

@Mapper(componentModel = "spring")
public interface UserCourseEnrollmentMapper extends EntityMapper<UserCourseEnrollmentdto, UserCourseEnrollmentEntity> {
	UserCourseEnrollmentdto toDto(Optional<UserCourseEnrollmentEntity> user);

	UserCourseEnrollmentdto toDto(UserCourseEnrollmentEntity user);

	UserCourseEnrollmentEntity toEntity(UserCourseEnrollmentdto d);

	List<UserCourseEnrollmentdto> toDto(List<UserCourseEnrollmentEntity> elist);

	List<UserCourseEnrollmentEntity> toEntity(List<UserCourseEnrollmentdto> dlist);
}
