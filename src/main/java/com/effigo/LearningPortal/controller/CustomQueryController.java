package com.effigo.LearningPortal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effigo.LearningPortal.service.impl.CustomqueryServiceimpl;
@RestController
@RequestMapping("/user")
public class CustomQueryController {
	private final CustomqueryServiceimpl customservice;
	@Autowired
	 public  CustomQueryController(CustomqueryServiceimpl customservice) {
	        this.customservice = customservice;
	    }
	 @GetMapping("/favorites")
	    public List<Object[]> getUsersWithFavoriteCourses() {
	        return customservice.getUsersWithFavoriteCourses();
	    }
     @GetMapping("enrolled")
     public List<Object[]> getUserWithEnrolledCourseNative()
     {
    	 return customservice.getUserWithEnrolledCourseNative();
     }
     @GetMapping("course/category")
     public List<Object[]> getCourseWithCategory(){
    	 return customservice.getCourseWithCategory();
     }
}
