package com.effigo.learning.portal.dto.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;

import com.effigo.learning.portal.dto.CategoryEntitydto;
import com.effigo.learning.portal.entity.CategoryEntity;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper extends EntityMapper<CategoryEntitydto, CategoryEntity> {
	CategoryEntitydto toDto(Optional<CategoryEntity> user);

	CategoryEntitydto toDto(CategoryEntity user);

	CategoryEntity toEntity(CategoryEntitydto d);

	List<CategoryEntitydto> toDto(List<CategoryEntity> elist);

	List<CategoryEntity> toEntity(List<CategoryEntitydto> dlist);
}
