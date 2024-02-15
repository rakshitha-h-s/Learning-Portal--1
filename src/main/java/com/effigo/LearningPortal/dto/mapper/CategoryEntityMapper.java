package com.effigo.LearningPortal.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.effigo.LearningPortal.dto.request.CategoryEntityrequest;
import com.effigo.LearningPortal.dto.response.CategoryEntityresponse;
import com.effigo.LearningPortal.entity.CategoryEntity;

@Mapper
public interface CategoryEntityMapper {
	CategoryEntityMapper MAPPER =Mappers.getMapper(CategoryEntityMapper.class);
	CategoryEntity fromRequestToEntity(CategoryEntityrequest courseentityRequest);
	CategoryEntityresponse fromEntityToResponse(CategoryEntity courseEntity);
}
