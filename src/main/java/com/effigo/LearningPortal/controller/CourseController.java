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
import com.effigo.LearningPortal.dto.response.CourseEntityResponse;
import com.effigo.LearningPortal.entity.CourseEntity;
import com.effigo.LearningPortal.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	private final CourseService userService;
	public CourseController(CourseService userService) {
		this.userService = userService;
	}
	@GetMapping
	public List<CourseEntity> findAllCourse()
	{
		return userService.findAllCourse();
	}
	@GetMapping("/{u_id}")
	public Optional<CourseEntity> findById(@PathVariable("u_id") Long id){
		return userService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteCourseEntity(@PathVariable("id") Long id) {
		userService.deleteCourseentity(id);
	}
	@PostMapping("/res")
	public CourseEntityResponse saveCourseResponse(@RequestBody CourseEntityrequest userRequest) {
		return userService.saveCourseEntity(userRequest);
	}
	@PutMapping("/update/{id}")
	public CourseEntityResponse updateCourseEntity(@RequestBody CourseEntityrequest userRequest,@PathVariable("id") Long id)
	{
		return userService.updateCourseEntity(userRequest, id);
	}

}
