package com.effigo.LearningPortal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.effigo.LearningPortal.repository.CustomQueryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomqueryServiceimpl {
	private final CustomQueryRepository customquery;

	public CustomqueryServiceimpl(CustomQueryRepository customquery) {
		this.customquery = customquery;
	}

	public List<Object[]> getUsersWithFavoriteCourses() {
		return customquery.getUsersWithFavoriteCoursesNative();
	}

	public List<Object[]> getUserWithEnrolledCourseNative() {
		return customquery.getUserWithEnrolledCourseNative();
	}

	public List<Object[]> getCourseWithCategory() {
		return customquery.getCourseWithCategory();
	}
}
