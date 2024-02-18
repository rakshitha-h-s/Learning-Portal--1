package com.effigo.LearningPortal.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.effigo.LearningPortal.dto.request.CourseEntityrequest;
import com.effigo.LearningPortal.dto.request.UserEntityrequest;
import com.effigo.LearningPortal.dto.response.CourseEntityResponse;
import com.effigo.LearningPortal.dto.response.UserEntityresponse;
import com.effigo.LearningPortal.entity.UserEntity;
import com.effigo.LearningPortal.entity.UserEntity.UserType;
import com.effigo.LearningPortal.service.UserEntityService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/userentity")
public class UserEntityController {
	private final UserEntityService userService;

	public UserEntityController(UserEntityService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserEntity> findAllUser() {
		log.info("all users");
		return userService.findAllUser();
	}

	@GetMapping("/{u_id}")
	public Optional<UserEntity> findById(@PathVariable("u_id") Long id) {
		log.info("Get users by id");
		return userService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteUserEntity(@PathVariable("id") Long id) {
		log.info("The user with"+id+" is deleted");
		userService.deleteUserentity(id);
	}

	@PostMapping("/res")
	public UserEntityresponse saveUserEntityResponse(@RequestBody UserEntityrequest userRequest) {
		log.info("The user is saved");
		return userService.saveUserEntity(userRequest);
	}

	@PutMapping("/update/{id}")
	public UserEntityresponse updateUserEntity(@RequestBody UserEntityrequest userRequest,
			@PathVariable("id") Long id) {
		log.info("The user with id"+id+"is updated");
		return userService.updateUserEntity(userRequest, id);
	}

	//"Admin" can create user accounts
	@PostMapping("/{usertype}/{id}/{password}")
	public UserEntityresponse saveUserEntityResponse1(@RequestBody UserEntityrequest userRequest,
			@PathVariable("usertype") UserType usertype, @PathVariable("id") Long id,
			@PathVariable("password") String password) {
		return userService.saveUserEntity1(userRequest, usertype, id, password);
	}

	@PostMapping("/addcourse/{usertype}/{id}/{password}")
	public CourseEntityResponse saveCourseEntityResponse(@RequestBody CourseEntityrequest userrequest,
			@PathVariable("usertype") UserType usertype, @PathVariable("id") Long id,
			@PathVariable("password") String password) {
		return userService.saveCourseEntity1(userrequest, usertype, id, password);
	}

	@PutMapping("/updatecourse/{usertype}/{id}/{password}/{courseid}")
	public CourseEntityResponse updateCourseEntityResponse(@RequestBody CourseEntityrequest userrequest,
			@PathVariable("usertype") UserType usertype, @PathVariable("id") Long id,
			@PathVariable("password") String password, @PathVariable("courseid") Long courseid) {
		return userService.updateCourseEntity1(userrequest, usertype, id, password, courseid);
	}

	@PostMapping("/addfav/{usertype}/{username}/{course_id}")
	public String addFavoriteEntity(@PathVariable("usertype") UserType usertype,
			@PathVariable("username") String username, @PathVariable("course_id") Long courseId) {
		return userService.saveFavoriteEntity(usertype, username, courseId);
	}

	@PostMapping("/enrollcourse/{usertype}/{username}/{course_id}")
	public String courseenrollment(@PathVariable("usertype") UserType usertype,
			@PathVariable("username") String username, @PathVariable("course_id") Long courseId) {
		return userService.courseenrollment(usertype, username, courseId);
	}
}
