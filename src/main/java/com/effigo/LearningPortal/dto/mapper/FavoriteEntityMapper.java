package com.effigo.LearningPortal.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mappings;
import com.effigo.LearningPortal.dto.request.CategoryEntityrequest;
import com.effigo.LearningPortal.dto.request.CourseEntityrequest;
import com.effigo.LearningPortal.dto.request.FavoriteEntityrequest;
import com.effigo.LearningPortal.dto.response.FavoriteEntityresponse;
import com.effigo.LearningPortal.entity.CourseEntity;
import com.effigo.LearningPortal.entity.FavoriteEntity;
@Mapper
public interface FavoriteEntityMapper {
	FavoriteEntityMapper MAPPER =Mappers.getMapper(FavoriteEntityMapper.class);
	FavoriteEntity fromRequestToEntity(FavoriteEntityrequest faventityRequest);
	FavoriteEntityresponse fromEntityToResponse(FavoriteEntity favEntity);

}
