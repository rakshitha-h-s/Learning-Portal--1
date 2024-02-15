package com.effigo.LearningPortal.service;

import java.util.List;
import java.util.Optional;

import com.effigo.LearningPortal.dto.request.FavoriteEntityrequest;
import com.effigo.LearningPortal.dto.request.UserEntityrequest;
import com.effigo.LearningPortal.dto.response.FavoriteEntityresponse;
import com.effigo.LearningPortal.dto.response.UserEntityresponse;
import com.effigo.LearningPortal.entity.FavoriteEntity;
import com.effigo.LearningPortal.entity.UserEntity;

public interface FavoriteService {
	List<FavoriteEntity> findAllFavorite();
	Optional<FavoriteEntity> findById(Long id);
	void deletefavoriteentity(Long id);
	FavoriteEntityresponse saveFavoriteEntity(FavoriteEntityrequest userentityrequest);
	FavoriteEntityresponse updateFavoriteEntity(FavoriteEntityrequest userentityrequest,Long id);
}
