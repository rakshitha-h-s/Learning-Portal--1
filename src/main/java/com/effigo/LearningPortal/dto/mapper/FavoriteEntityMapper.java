package com.effigo.LearningPortal.dto.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;

import com.effigo.LearningPortal.dto.FavoriteEntitydto;
import com.effigo.LearningPortal.entity.FavoriteEntity;

@Mapper(componentModel = "spring")
public interface FavoriteEntityMapper extends EntityMapper<FavoriteEntitydto, FavoriteEntity> {
	FavoriteEntitydto toDto(Optional<FavoriteEntity> user);

	FavoriteEntitydto toDto(FavoriteEntity user);

	FavoriteEntity toEntity(FavoriteEntitydto d);

	List<FavoriteEntitydto> toDto(List<FavoriteEntity> elist);

	List<FavoriteEntity> toEntity(List<FavoriteEntitydto> dlist);
}
