package com.effigo.LearningPortal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effigo.LearningPortal.dto.FavoriteEntitydto;
import com.effigo.LearningPortal.dto.mapper.FavoriteEntityMapper;
import com.effigo.LearningPortal.entity.FavoriteEntity;
import com.effigo.LearningPortal.repository.FavoriteEntityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FavoriteServiceImpl {
	@Autowired
	FavoriteEntityRepository favoriteRepository;
	@Autowired
	FavoriteEntityMapper favMapper;

	public FavoriteServiceImpl(FavoriteEntityRepository favoriteRepository) {
		this.favoriteRepository = favoriteRepository;
	}

	public List<FavoriteEntitydto> findAllFavorite() {
		List<FavoriteEntity> user = favoriteRepository.findAll();
		return favMapper.toDto(user);
	}

	public FavoriteEntitydto findById(Long id) {
		Optional<FavoriteEntity> user = favoriteRepository.findById(id);
		FavoriteEntity entity = user.get();
		return favMapper.toDto(entity);
	}

	public FavoriteEntitydto saveFavoriteEntity(FavoriteEntitydto userentityrequest) {
		FavoriteEntity favEntity = favMapper.toEntity(userentityrequest);
		favoriteRepository.save(favEntity);
		return favMapper.toDto(favEntity);
	}

	public FavoriteEntitydto updateFavoriteEntity(FavoriteEntitydto userentityrequest, Long id) {
		Optional<FavoriteEntity> checkExistinguser = favoriteRepository.findById(id);
		if (!checkExistinguser.isPresent())
			log.error("Favorite Id " + id + " Not Found!");
		FavoriteEntity favEntity = favMapper.toEntity(userentityrequest);
		favoriteRepository.save(favEntity);
		return favMapper.toDto(favEntity);
	}

	public void deletefavoriteentity(Long id) {
		favoriteRepository.deleteById(id);
	}

}
