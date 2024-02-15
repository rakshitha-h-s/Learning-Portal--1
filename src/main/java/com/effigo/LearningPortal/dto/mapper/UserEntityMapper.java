package com.effigo.LearningPortal.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.effigo.LearningPortal.dto.request.UserEntityrequest;
import com.effigo.LearningPortal.dto.response.UserEntityresponse;
import com.effigo.LearningPortal.entity.UserEntity;

@Mapper
public interface UserEntityMapper {
	UserEntityMapper MAPPER =Mappers.getMapper(UserEntityMapper.class);
	UserEntity fromRequestToEntity(UserEntityrequest userentityRequest);
	UserEntityresponse fromEntityToResponse(UserEntity userEntity);
	}	

