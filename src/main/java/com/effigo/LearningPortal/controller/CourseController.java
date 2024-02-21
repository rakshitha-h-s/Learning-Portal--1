package com.effigo.LearningPortal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effigo.LearningPortal.dto.CourseEntitydto;
import com.effigo.LearningPortal.service.CourseServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/course")
public class CourseController {
	private final CourseServiceImpl userService;

	public CourseController(CourseServiceImpl userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<CourseEntitydto> findAllCourse() {
		return userService.findAllCourse();
	}

	@GetMapping("/{u_id}")
	public CourseEntitydto findById(@PathVariable("u_id") Long id) {
		log.info("the course with course id" + id);
		return userService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteCourseEntity(@PathVariable("id") Long id) {
		log.info("the course with id" + id + "is deleted");
		userService.deleteCourseentity(id);
	}

	@PostMapping("/res")
	public CourseEntitydto saveCourseResponse(@RequestBody CourseEntitydto userRequest) {
		log.info("the course is saved");
		return userService.saveCourseEntity(userRequest);
	}

	@PutMapping("/update/{id}")
	public CourseEntitydto updateCourseEntity(@RequestBody CourseEntitydto userRequest, @PathVariable("id") Long id) {
		log.info("course is updated");
		return userService.updateCourseEntity(userRequest, id);
	}

}
