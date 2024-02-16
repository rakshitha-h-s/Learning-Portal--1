package com.effigo.LearningPortal.service;

import java.util.List;
import java.util.Optional;
import com.effigo.LearningPortal.dto.request.FavoriteEntityrequest;
import com.effigo.LearningPortal.dto.response.FavoriteEntityresponse;
import com.effigo.LearningPortal.entity.FavoriteEntity;

public interface FavoriteService {
	List<FavoriteEntity> findAllFavorite();
	Optional<FavoriteEntity> findById(Long id);
	Optional<FavoriteEntity> findFavoriteByCourseIdAndUId(Long courseId, Long uId);
	FavoriteEntityresponse saveFavoriteEntity(FavoriteEntityrequest userentityrequest);
	FavoriteEntityresponse updateFavoriteEntity(FavoriteEntityrequest userentityrequest,Long id);
	void deletefavoriteentity(Long id);
}
