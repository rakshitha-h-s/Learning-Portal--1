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

import com.effigo.learning.portal.dto.UserCourseEnrollmentdto;
import com.effigo.learning.portal.dto.mapper.UserCourseEnrollmentMapper;
import com.effigo.learning.portal.entity.CourseEntity;
import com.effigo.learning.portal.entity.UserCourseEnrollmentEntity;
import com.effigo.learning.portal.entity.UserEntity;
import com.effigo.learning.portal.repository.UserCourseEnrollmentRepository;

@ExtendWith(MockitoExtension.class)
public class CourseEnrollTest {
	@Mock
	private UserCourseEnrollmentRepository enrollRepository;

	@Mock
	private UserCourseEnrollmentMapper enrollMapper;

	@InjectMocks
	private UserCourseEnrollmentServiceImpl enrollService;
	private UserEntity userEntity;
	private CourseEntity courseEntity;
	private UserCourseEnrollmentEntity enroll;
	private UserCourseEnrollmentdto udto;

	@BeforeEach
	void setUp() {
		userEntity = new UserEntity();
		userEntity.setuId(1L);

		courseEntity = new CourseEntity();
		courseEntity.setCourseId("course-01");

		enroll = new UserCourseEnrollmentEntity();
		enroll.setEnrollmentId(1L);
		enroll.setUId(userEntity);
		enroll.setCourseId(courseEntity);
		udto = new UserCourseEnrollmentdto();
		udto.setEnrollmentId(1L);
		udto.setUId(userEntity);
		udto.setCourseId(courseEntity);

	}

	@Test
	void testSaveFavourite() {
		when(enrollMapper.toEntity(udto)).thenReturn(enroll);
		when(enrollRepository.save(any())).thenReturn(enroll);
		when(enrollMapper.toDto(enroll)).thenReturn(udto);
		UserCourseEnrollmentdto savedFavourite = enrollService.saveUserCourseEnrollmentEntity(udto);

		assertNotNull(savedFavourite);
		assertEquals(udto, savedFavourite);
	}

	@Test
	void testGetAllFavourites() {
		List<UserCourseEnrollmentEntity> favouriteEntities = new ArrayList<>();
		favouriteEntities.add(enroll);
		when(enrollRepository.findAll()).thenReturn(favouriteEntities);

		List<UserCourseEnrollmentdto> allFavourites = enrollService.findAllCourseEnrollment();

		assertNotNull(allFavourites);

	}

	@Test
	void testGetFavouriteById_FavouriteExists() {
		when(enrollRepository.findById(1L)).thenReturn(Optional.of(enroll));
		when(enrollMapper.toDto(enroll)).thenReturn(udto);
		UserCourseEnrollmentdto foundFavourite = enrollService.findById(1L);

		assertNotNull(foundFavourite);
		assertEquals(udto, foundFavourite);
	}

	@Test
	void testGetFavouriteById_FavouriteNotExists() {
		when(enrollRepository.findById(2L)).thenReturn(Optional.empty());

		UserCourseEnrollmentdto foundFavourite = enrollService.findById(2L);

		assertNull(foundFavourite);
	}

	@Test
	void testDeleteFavourite() {
		assertDoesNotThrow(() -> enrollService.deleteUserCourseEnrollmententity(1L));
		verify(enrollRepository).deleteById(1L);
	}

}
