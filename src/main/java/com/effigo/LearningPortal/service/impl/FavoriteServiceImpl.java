package com.effigo.LearningPortal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.effigo.LearningPortal.dto.mapper.FavoriteEntityMapper;
import com.effigo.LearningPortal.dto.mapper.UserEntityMapper;
import com.effigo.LearningPortal.dto.request.FavoriteEntityrequest;
import com.effigo.LearningPortal.dto.response.FavoriteEntityresponse;
import com.effigo.LearningPortal.entity.FavoriteEntity;
import com.effigo.LearningPortal.entity.UserEntity;
import com.effigo.LearningPortal.repository.CourseEntityRepository;
import com.effigo.LearningPortal.repository.FavoriteEntityRepository;
import com.effigo.LearningPortal.service.FavoriteService;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {
	private final FavoriteEntityRepository favoriteRepository;
	public FavoriteServiceImpl(FavoriteEntityRepository favoriteRepository)
	{
		this.favoriteRepository=favoriteRepository;
	}
	@Override
	public List<FavoriteEntity> findAllFavorite() {
		return favoriteRepository.findAll();
	}

	@Override
	public Optional<FavoriteEntity> findById(Long id) {
		return favoriteRepository.findById(id);
	}

	@Override
	public void deletefavoriteentity(Long id) {
		favoriteRepository.deleteById(id);
	}

	@Override
	public FavoriteEntityresponse saveFavoriteEntity(FavoriteEntityrequest userentityrequest) {
		FavoriteEntity userEntity = FavoriteEntityMapper.MAPPER.fromRequestToEntity(userentityrequest);
        favoriteRepository.save(userEntity);
        return FavoriteEntityMapper.MAPPER.fromEntityToResponse(userEntity);
	}
	 

	@Override
	public FavoriteEntityresponse updateFavoriteEntity(FavoriteEntityrequest userentityrequest, Long id) {
		Optional<FavoriteEntity> checkExistinguser = findById(id);
        if (! checkExistinguser.isPresent())
            throw new RuntimeException("Favorite Id "+ id + " Not Found!");
        FavoriteEntity userEntity =FavoriteEntityMapper.MAPPER.fromRequestToEntity(userentityrequest);
        favoriteRepository.save(userEntity);
        return FavoriteEntityMapper.MAPPER.fromEntityToResponse(userEntity);
	}
	
}
