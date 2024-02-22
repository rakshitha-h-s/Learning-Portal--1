package com.effigo.learning.portal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effigo.learning.portal.dto.CategoryEntitydto;
import com.effigo.learning.portal.dto.mapper.CategoryEntityMapper;
import com.effigo.learning.portal.entity.CategoryEntity;
import com.effigo.learning.portal.repository.CategoryEntityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryServiceImpl {
	@Autowired
	CategoryEntityRepository categoryRepository;

	public List<CategoryEntitydto> findAllCategory() {
		List<CategoryEntity> user = categoryRepository.findAll();
		return categorymapper.toDto(user);
	}

	@Autowired
	CategoryEntityMapper categorymapper;

	public CategoryEntitydto findById(String id) {
		Optional<CategoryEntity> user = categoryRepository.findById(id);
		return categorymapper.toDto(user);
	}

	public void deleteCategoryentity(String id) {
		categoryRepository.deleteById(id);
	}

	public CategoryEntitydto saveCategoryEntity(CategoryEntitydto userentityrequest) {
		CategoryEntity userEntity = categorymapper.toEntity(userentityrequest);
		categoryRepository.save(userEntity);
		return categorymapper.toDto(userEntity);
	}

	public CategoryEntitydto updateCategoryEntity(CategoryEntitydto userentityrequest, String uid) {
		Optional<CategoryEntity> checkExistinguser = categoryRepository.findById(uid);
		if (!checkExistinguser.isPresent())
			log.error("Category Id " + uid + " Not Found!");
		CategoryEntity userEntity = categorymapper.toEntity(userentityrequest);
		categoryRepository.save(userEntity);
		return categorymapper.toDto(userEntity);

	}

}
