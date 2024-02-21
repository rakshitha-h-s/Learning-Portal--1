package com.effigo.LearningPortal.dto.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;

import com.effigo.LearningPortal.dto.UserEntitydto;
import com.effigo.LearningPortal.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserEntityMapper extends EntityMapper<UserEntitydto, UserEntity> {
	UserEntitydto toDto(Optional<UserEntity> user);

	UserEntitydto toDto(UserEntity user);

	UserEntity toEntity(UserEntitydto d);

	List<UserEntitydto> toDto(List<UserEntity> elist);

	List<UserEntity> toEntity(List<UserEntitydto> dlist);

}
