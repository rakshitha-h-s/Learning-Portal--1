package com.effigo.LearningPortal.dto.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;

import com.effigo.LearningPortal.dto.CourseEntitydto;
import com.effigo.LearningPortal.entity.CourseEntity;

@Mapper(componentModel = "spring")
public interface CourseEntityMapper extends EntityMapper<CourseEntitydto, CourseEntity> {
	CourseEntitydto toDto(Optional<CourseEntity> user);

	CourseEntitydto toDto(CourseEntity user);

	CourseEntity toEntity(CourseEntitydto d);

	List<CourseEntitydto> toDto(List<CourseEntity> elist);

	List<CourseEntity> toEntity(List<CourseEntitydto> dlist);
}
