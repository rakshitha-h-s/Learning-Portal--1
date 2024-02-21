package com.effigo.LearningPortal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effigo.LearningPortal.service.CustomqueryServiceimpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class CustomQueryController {
	private final CustomqueryServiceimpl customservice;

	public CustomQueryController(CustomqueryServiceimpl customservice) {

		this.customservice = customservice;
	}

	@GetMapping("/favorites")
	public List<Object[]> getUsersWithFavoriteCourses() {
		log.info("fetching users along with favorites");
		return customservice.getUsersWithFavoriteCourses();
	}

	@GetMapping("enrolled")
	public List<Object[]> getUserWithEnrolledCourseNative() {
		log.info("fetching users along with their enrolled course");
		return customservice.getUserWithEnrolledCourseNative();
	}

	@GetMapping("course/category")
	public List<Object[]> getCourseWithCategory() {
		log.info("fetching course with the category");
		return customservice.getCourseWithCategory();
	}
}
