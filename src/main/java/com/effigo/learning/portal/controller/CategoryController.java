package com.effigo.learning.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effigo.learning.portal.dto.CategoryEntitydto;
import com.effigo.learning.portal.service.CategoryServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryServiceImpl userService;

	@GetMapping
	public List<CategoryEntitydto> findAllCategory() {
		log.info("the category");
		return userService.findAllCategory();
	}

	@GetMapping("/{u_id}")
	public CategoryEntitydto findById(@PathVariable("u_id") String id) {
		log.info("category based on id" + id);
		return userService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteCategoryEntity(@PathVariable("id") String id) {
		log.info("deleted the category with id" + id);
		userService.deleteCategoryentity(id);
	}

	@PostMapping("/res")
	public CategoryEntitydto saveCategoryResponse(@RequestBody CategoryEntitydto userRequest) {
		log.info("saved category");
		return userService.saveCategoryEntity(userRequest);
	}

	@PutMapping("/update/{id}")
	public CategoryEntitydto updateCategoryEntity(@RequestBody CategoryEntitydto userRequest,
			@PathVariable("id") String id) {
		log.info("updated category");
		return userService.updateCategoryEntity(userRequest, id);
	}
}
