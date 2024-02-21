package com.effigo.LearningPortal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effigo.LearningPortal.dto.CategoryEntitydto;
import com.effigo.LearningPortal.dto.mapper.CategoryEntityMapper;
import com.effigo.LearningPortal.entity.CategoryEntity;
import com.effigo.LearningPortal.repository.CategoryEntityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryServiceImpl {
	private final CategoryEntityRepository categoryRepository;

	public CategoryServiceImpl(CategoryEntityRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<CategoryEntitydto> findAllCategory() {
		List<CategoryEntity> user = categoryRepository.findAll();
		return categorymapper.toDto(user);
	}

	@Autowired
	CategoryEntityMapper categorymapper;

	public CategoryEntitydto findById(Long id) {
		Optional<CategoryEntity> user = categoryRepository.findById(id);
		return categorymapper.toDto(user);
	}

	public void deleteCategoryentity(Long id) {
		categoryRepository.deleteById(id);
	}

	public CategoryEntitydto saveCategoryEntity(CategoryEntitydto userentityrequest) {
		CategoryEntity userEntity = categorymapper.toEntity(userentityrequest);
		categoryRepository.save(userEntity);
		return categorymapper.toDto(userEntity);
	}

	public CategoryEntitydto updateCategoryEntity(CategoryEntitydto userentityrequest, Long uid) {
		Optional<CategoryEntity> checkExistinguser = categoryRepository.findById(uid);
		if (!checkExistinguser.isPresent())
			log.error("Category Id " + uid + " Not Found!");
		CategoryEntity userEntity = categorymapper.toEntity(userentityrequest);
		categoryRepository.save(userEntity);
		return categorymapper.toDto(userEntity);

	}

}
