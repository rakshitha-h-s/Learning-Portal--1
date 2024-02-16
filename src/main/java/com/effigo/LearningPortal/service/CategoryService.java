package com.effigo.LearningPortal.service;

import java.util.List;
import java.util.Optional;
import com.effigo.LearningPortal.dto.request.CategoryEntityrequest;
import com.effigo.LearningPortal.dto.response.CategoryEntityresponse;
import com.effigo.LearningPortal.entity.CategoryEntity;

public interface CategoryService {
	List<CategoryEntity> findAllCategory();
	Optional<CategoryEntity> findById(Long id);
	void deleteCategoryentity(Long id);
	CategoryEntityresponse saveCategoryEntity(CategoryEntityrequest userentityrequest);
	CategoryEntityresponse updateCategoryEntity(CategoryEntityrequest userentityrequest,Long id);
}
