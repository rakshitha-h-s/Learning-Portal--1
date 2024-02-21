package com.effigo.LearningPortal.dto.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;

import com.effigo.LearningPortal.dto.CategoryEntitydto;
import com.effigo.LearningPortal.entity.CategoryEntity;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper extends EntityMapper<CategoryEntitydto, CategoryEntity> {
	CategoryEntitydto toDto(Optional<CategoryEntity> user);

	CategoryEntitydto toDto(CategoryEntity user);

	CategoryEntity toEntity(CategoryEntitydto d);

	List<CategoryEntitydto> toDto(List<CategoryEntity> elist);

	List<CategoryEntity> toEntity(List<CategoryEntitydto> dlist);
}
