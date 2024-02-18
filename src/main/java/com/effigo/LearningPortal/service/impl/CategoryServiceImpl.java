package com.effigo.LearningPortal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.effigo.LearningPortal.dto.mapper.CategoryEntityMapper;
import com.effigo.LearningPortal.dto.request.CategoryEntityrequest;
import com.effigo.LearningPortal.dto.response.CategoryEntityresponse;
import com.effigo.LearningPortal.entity.CategoryEntity;
import com.effigo.LearningPortal.repository.CategoryEntityRepository;
import com.effigo.LearningPortal.service.CategoryService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	private final CategoryEntityRepository categoryRepository;

	public CategoryServiceImpl(CategoryEntityRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<CategoryEntity> findAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public Optional<CategoryEntity> findById(Long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public void deleteCategoryentity(Long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public CategoryEntityresponse saveCategoryEntity(CategoryEntityrequest userentityrequest) {
		CategoryEntity userEntity = CategoryEntityMapper.MAPPER.fromRequestToEntity(userentityrequest);
		categoryRepository.save(userEntity);
		return CategoryEntityMapper.MAPPER.fromEntityToResponse(userEntity);
	}

	@Override
	public CategoryEntityresponse updateCategoryEntity(CategoryEntityrequest userentityrequest, Long uid) {
		Optional<CategoryEntity> checkExistinguser = findById(uid);
		if (!checkExistinguser.isPresent())
			log.error("Category Id " + uid + " Not Found!");
		CategoryEntity userEntity = CategoryEntityMapper.MAPPER.fromRequestToEntity(userentityrequest);
		categoryRepository.save(userEntity);
		return CategoryEntityMapper.MAPPER.fromEntityToResponse(userEntity);
	}

}
