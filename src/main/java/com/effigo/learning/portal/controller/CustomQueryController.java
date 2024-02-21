package com.effigo.learning.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effigo.learning.portal.service.CustomqueryServiceimpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class CustomQueryController {
	@Autowired
	CustomqueryServiceimpl customservice;

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
