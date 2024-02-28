package com.effigo.learning.portal.dto.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;

import com.effigo.learning.portal.dto.FavoriteEntitydto;
import com.effigo.learning.portal.entity.FavoriteEntity;

@Mapper(componentModel = "spring")
public interface FavoriteEntityMapper extends EntityMapper<FavoriteEntitydto, FavoriteEntity> {

	FavoriteEntitydto toDto(Optional<FavoriteEntity> user);

	FavoriteEntitydto toDto(FavoriteEntity user);

	FavoriteEntity toEntity(FavoriteEntitydto d);

	List<FavoriteEntitydto> toDto(List<FavoriteEntity> elist);

	List<FavoriteEntity> toEntity(List<FavoriteEntitydto> dlist);
}
