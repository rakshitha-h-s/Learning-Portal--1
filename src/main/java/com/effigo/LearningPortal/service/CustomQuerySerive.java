package com.effigo.LearningPortal.service;

import java.util.List;

public interface CustomQuerySerive {
	List<Object[]> getUsersWithFavoriteCourses();
	List<Object[]> getUserWithEnrolledCourseNative();
	List<Object[]> getCourseWithCategory();

}
