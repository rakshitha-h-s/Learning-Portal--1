package com.effigo.LearningPortal.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.effigo.LearningPortal.dto.request.CourseEntityrequest;
import com.effigo.LearningPortal.dto.response.CourseEntityResponse;
import com.effigo.LearningPortal.entity.CourseEntity;
@Mapper
public interface CourseEntityMapper {
	CourseEntityMapper MAPPER =Mappers.getMapper(CourseEntityMapper.class);
	CourseEntity fromRequestToEntity(CourseEntityrequest courseentityRequest);
	CourseEntityResponse fromEntityToResponse(CourseEntity courseEntity);
}
