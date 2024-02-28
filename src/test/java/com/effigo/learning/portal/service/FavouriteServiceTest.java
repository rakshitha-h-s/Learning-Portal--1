package com.effigo.learning.portal.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.effigo.learning.portal.dto.FavoriteEntitydto;
import com.effigo.learning.portal.dto.mapper.FavoriteEntityMapper;
import com.effigo.learning.portal.entity.CourseEntity;
import com.effigo.learning.portal.entity.FavoriteEntity;
import com.effigo.learning.portal.entity.UserEntity;
import com.effigo.learning.portal.repository.FavoriteEntityRepository;

@ExtendWith(MockitoExtension.class)
class FavouriteServiceTest {

	@Mock
	private FavoriteEntityRepository favouriteRepository;

	@Mock
	private FavoriteEntityMapper favouriteMapper;

	@InjectMocks
	private FavoriteServiceImpl favouriteService;

	private FavoriteEntity favouriteEntity;
	private FavoriteEntitydto favouriteDto;
	private UserEntity userEntity;
	private CourseEntity courseEntity;

	@BeforeEach
	void setUp() {
		userEntity = new UserEntity();
		userEntity.setuId(1L);

		courseEntity = new CourseEntity();
		courseEntity.setCourseId("course-01");

		favouriteEntity = new FavoriteEntity();
		favouriteEntity.setfId(1L);
		favouriteEntity.setuId(userEntity);
		favouriteEntity.setCourseId(courseEntity);

		favouriteDto = new FavoriteEntitydto();
		favouriteDto.setfId(1L);
		favouriteDto.setuId(userEntity);
		favouriteDto.setCourseId(courseEntity);

	}

	@Test
	void testSaveFavourite() {
		when(favouriteMapper.toEntity(favouriteDto)).thenReturn(favouriteEntity);
		when(favouriteRepository.save(any())).thenReturn(favouriteEntity);
		when(favouriteMapper.toDto(favouriteEntity)).thenReturn(favouriteDto);
		FavoriteEntitydto savedFavourite = favouriteService.saveFavoriteEntity(favouriteDto);

		assertNotNull(savedFavourite);
		assertEquals(favouriteDto, savedFavourite);
	}

	@Test
	void testGetAllFavourites() {
		List<FavoriteEntity> favouriteEntities = new ArrayList<>();
		favouriteEntities.add(favouriteEntity);
		when(favouriteRepository.findAll()).thenReturn(favouriteEntities);

		List<FavoriteEntitydto> allFavourites = favouriteService.findAllFavorite();

		assertNotNull(allFavourites);

	}

	@Test
	void testGetFavouriteById_FavouriteExists() {
		when(favouriteRepository.findById(1L)).thenReturn(Optional.of(favouriteEntity));
		when(favouriteMapper.toDto(favouriteEntity)).thenReturn(favouriteDto);
		FavoriteEntitydto foundFavourite = favouriteService.findById(1L);

		assertNotNull(foundFavourite);
		assertEquals(favouriteDto, foundFavourite);
	}

	@Test
	void testGetFavouriteById_FavouriteNotExists() {
		when(favouriteRepository.findById(2L)).thenReturn(Optional.empty());

		FavoriteEntitydto foundFavourite = favouriteService.findById(2L);

		assertNull(foundFavourite);
	}

	@Test
	void testDeleteFavourite() {
		assertDoesNotThrow(() -> favouriteService.deletefavoriteentity(1L));
		verify(favouriteRepository).deleteById(1L);
	}

}