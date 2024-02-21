package com.effigo.learning.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effigo.learning.portal.repository.CustomQueryRepository;

@Service
public class CustomqueryServiceimpl {
	@Autowired
	CustomQueryRepository customquery;

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
