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

import com.effigo.learning.portal.dto.FavoriteEntitydto;
import com.effigo.learning.portal.service.FavoriteServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/favorite")
public class FavoriteController {
	@Autowired
	FavoriteServiceImpl favService;

	@GetMapping
	public List<FavoriteEntitydto> findAllFavorite() {
		log.info("all the favorites");
		return favService.findAllFavorite();
	}

	@GetMapping("/{u_id}")
	public FavoriteEntitydto findById(@PathVariable("u_id") Long id) {
		log.info("showing favorite for id" + id);
		return favService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteeavoriteentity(@PathVariable("id") Long id) {
		log.info("deleting favorite of id" + id);
		favService.deletefavoriteentity(id);
	}

	@PostMapping("/res")
	public FavoriteEntitydto saveUserEntityResponse(@RequestBody FavoriteEntitydto userRequest) {
		log.info("saving the favorite");
		return favService.saveFavoriteEntity(userRequest);
	}

	@PutMapping("/update/{id}")
	public FavoriteEntitydto updateFavoriteEntity(@RequestBody FavoriteEntitydto userRequest,
			@PathVariable("id") Long id) {
		log.info("updating the favorite");
		return favService.updateFavoriteEntity(userRequest, id);
	}

}
