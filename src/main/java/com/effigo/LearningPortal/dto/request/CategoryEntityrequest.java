package com.effigo.LearningPortal.dto.request;

import java.io.Serializable;

import com.effigo.LearningPortal.entity.CategoryEntity.CategoryType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntityrequest implements Serializable {
	private Long categoryId;
	private CategoryType categoryType;
}
