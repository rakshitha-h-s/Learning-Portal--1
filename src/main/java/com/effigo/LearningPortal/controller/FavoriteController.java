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
import com.effigo.LearningPortal.dto.request.FavoriteEntityrequest;
import com.effigo.LearningPortal.dto.request.UserEntityrequest;
import com.effigo.LearningPortal.dto.response.FavoriteEntityresponse;
import com.effigo.LearningPortal.dto.response.UserEntityresponse;
import com.effigo.LearningPortal.entity.FavoriteEntity;
import com.effigo.LearningPortal.entity.UserEntity;
import com.effigo.LearningPortal.service.FavoriteService;
import com.effigo.LearningPortal.service.UserEntityService;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
	private final FavoriteService favService;
	public FavoriteController(FavoriteService favService) {
		this.favService = favService;
	}
	@GetMapping
	public List<FavoriteEntity> findAllFavorite()
	{
		return favService.findAllFavorite();
	}
	@GetMapping("/{u_id}")
	public Optional<FavoriteEntity> findById(@PathVariable("u_id") Long id){
		return favService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteeavoriteentity(@PathVariable("id") Long id) {
		favService.deletefavoriteentity(id);
	}
	@PostMapping("/res")
	public FavoriteEntityresponse saveUserEntityResponse(@RequestBody FavoriteEntityrequest userRequest) {
		return favService.saveFavoriteEntity(userRequest);
	}
	@PutMapping("/update/{id}")
	public FavoriteEntityresponse updateFavoriteEntity(@RequestBody FavoriteEntityrequest userRequest,@PathVariable("id") Long id)
	{
		return favService.updateFavoriteEntity(userRequest, id);
	}


}
