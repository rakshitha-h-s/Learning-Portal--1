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

import com.effigo.LearningPortal.dto.UserCourseEnrollmentdto;
import com.effigo.LearningPortal.service.UserCourseEnrollmentServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/usercourseenrollment")
public class UserCourseEnrollmentController {
	private final UserCourseEnrollmentServiceImpl usercourseenrollmentService;

	public UserCourseEnrollmentController(UserCourseEnrollmentServiceImpl usercourseenrollmentService) {
		this.usercourseenrollmentService = usercourseenrollmentService;
	}

	@GetMapping
	public List<UserCourseEnrollmentdto> findAllCourseEnrollment() {
		log.info("all the course enrollment list");
		return usercourseenrollmentService.findAllCourseEnrollment();
	}

	@GetMapping("/{u_id}")
	public UserCourseEnrollmentdto findById(@PathVariable("u_id") Long id) {
		log.info("the user with id" + id + "details");
		return usercourseenrollmentService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteUserCourseEnrollmententity(@PathVariable("id") Long id) {
		log.info("course enrollment with id" + id + "is deleted");
		usercourseenrollmentService.deleteUserCourseEnrollmententity(id);
	}

	@PostMapping("/res")
	public UserCourseEnrollmentdto saveUserEntityResponse(@RequestBody UserCourseEnrollmentdto userRequest) {
		log.info("saved new course entity");
		return usercourseenrollmentService.saveUserCourseEnrollmentEntity(userRequest);
	}

	@PutMapping("/update/{id}")
	public UserCourseEnrollmentdto updateUserCourseEnrollmentEntity(@RequestBody UserCourseEnrollmentdto userRequest,
			@PathVariable("id") Long id) {
		log.info("updated course enrollment");
		return usercourseenrollmentService.updateUserCourseEnrollmentEntity(userRequest, id);
	}
}
