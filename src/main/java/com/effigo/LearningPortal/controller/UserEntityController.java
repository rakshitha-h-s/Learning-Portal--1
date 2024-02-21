package com.effigo.LearningPortal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.effigo.LearningPortal.dto.CourseEntitydto;
import com.effigo.LearningPortal.dto.UserEntitydto;
import com.effigo.LearningPortal.entity.UserEntity.UserType;
import com.effigo.LearningPortal.service.UserEntityServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/userentity")
public class UserEntityController {
	private final UserEntityServiceImpl userService;

	public UserEntityController(UserEntityServiceImpl userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserEntitydto> findAllUser() {
		log.info("all users");
		return userService.findAllUser();
	}

	@GetMapping("/{u_id}")
	public UserEntitydto findById(@PathVariable("u_id") Long id) {
		log.info("Get users by id");
		return userService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteUserEntity(@PathVariable("id") Long id) {
		log.info("The user with" + id + " is deleted");
		userService.deleteUserentity(id);
	}

	@PostMapping("/res")
	public UserEntitydto saveUserEntityResponse(@RequestBody UserEntitydto userRequest) {
		log.info("The user is saved");
		return userService.saveUserEntity(userRequest);
	}

	@PutMapping("/update/{id}")
	public UserEntitydto updateUserEntity(@RequestBody UserEntitydto userRequest, @PathVariable("id") Long id) {
		log.info("The user with id" + id + "is updated");
		return userService.updateUserEntity(userRequest, id);
	}

	//"Admin" can create user accounts
	@PostMapping("/{usertype}")
	public UserEntitydto saveUserEntityResponse1(@RequestBody UserEntitydto userRequest,
			@PathVariable("usertype") UserType usertype, @RequestParam("id") Long id,
			@RequestParam("username") String username, @RequestParam("password") String password) {
		return userService.saveUserEntity1(userRequest, usertype, id, username, password);
	}

	@PostMapping("/addcourse/{usertype}")
	public CourseEntitydto saveCourseEntityResponse(@RequestBody CourseEntitydto userrequest,
			@PathVariable("usertype") UserType usertype, @RequestParam("id") Long id,
			@RequestParam("username") String username, @RequestParam("password") String password) {
		return userService.saveCourseEntity1(userrequest, usertype, id, username, password);
	}

	@PutMapping("/updatecourse/{usertype}/{courseid}")
	public CourseEntitydto updateCourseEntityResponse(@RequestBody CourseEntitydto userrequest,
			@PathVariable("usertype") UserType usertype, @RequestParam("id") Long id,
			@RequestParam("username") String username, @RequestParam("password") String password,
			@PathVariable("courseid") Long courseid) {
		return userService.updateCourseEntity1(userrequest, usertype, id, username, password, courseid);
	}

	@PostMapping("/addfav/{usertype}/{course_id}")
	public String addFavoriteEntity(@PathVariable("usertype") UserType usertype, @RequestParam("id") Long id,
			@RequestParam("username") String username, @RequestParam("password") String password,
			@PathVariable("course_id") Long courseId) {
		return userService.saveFavoriteEntity(usertype, id, username, password, courseId);
	}

	@PostMapping("/enrollcourse/{usertype}/{course_id}")
	public String courseenrollment(@PathVariable("usertype") UserType usertype, @RequestParam("id") Long id,
			@RequestParam("username") String username, @RequestParam("password") String password,
			@PathVariable("course_id") Long courseId) {
		return userService.courseenrollment(usertype, id, username, password, courseId);
	}
}
