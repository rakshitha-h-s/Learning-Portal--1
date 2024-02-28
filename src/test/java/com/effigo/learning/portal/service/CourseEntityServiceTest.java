package com.effigo.learning.portal.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.effigo.learning.portal.dto.CourseEntitydto;
import com.effigo.learning.portal.dto.mapper.CourseEntityMapper;
import com.effigo.learning.portal.entity.CategoryEntity;
import com.effigo.learning.portal.entity.CourseEntity;
import com.effigo.learning.portal.entity.UserEntity;
import com.effigo.learning.portal.repository.CourseEntityRepository;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class CourseEntityServiceTest {
	@Mock
	private CourseEntityRepository courseEntityRepository;

	@Mock
	private CourseEntityMapper courseMapper;

	@InjectMocks
	private CourseServiceImpl courseService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindAllCourse() {
		CourseEntity course1 = new CourseEntity();
		course1.setCourseId("course-01");
		course1.setCourseName("java fullstack");
		CategoryEntity cat = new CategoryEntity();
		cat.setCategoryId("cat-01");
		course1.setCategory(cat);
		UserEntity user1 = new UserEntity();
		user1.setuId(1L);
		course1.setId(user1);

		List<CourseEntity> userList = Arrays.asList(course1);

		when(courseEntityRepository.findAll()).thenReturn(userList);

		CourseEntitydto user1Dto = new CourseEntitydto();
		user1Dto.setCourseName("java");
		when(courseMapper.toDto(userList)).thenReturn(Arrays.asList(user1Dto));

		List<CourseEntitydto> result = courseService.findAllCourse();

		assertThat(result).isNotNull();
		assertThat(result).hasSize(1);
		assertThat(result.get(0).getCourseName()).isEqualTo("java");
		//assertThat(result.get(1).getUsername()).isEqualTo("user2");

		verify(courseEntityRepository, times(1)).findAll();

		verify(courseMapper, times(1)).toDto(userList);
		// Mock data
	}

	@Test
	public void testFindById() {
		// Mock data
		CourseEntity courseEntity = new CourseEntity();
		when(courseMapper.toDto(any(CourseEntity.class))).thenReturn(new CourseEntitydto());

		String id = "course-01";
		when(courseEntityRepository.findById(id)).thenReturn(Optional.of(courseEntity));

		CourseEntitydto result = courseService.findById(id);

		assertNotNull(result);
	}

	@Test
	public void testDeleteCourseentity() {
		// Test
		courseService.deleteCourseentity("1");

		// Verify
		verify(courseEntityRepository).deleteById("1");
	}

	@Test
	public void testSaveCourseEntity() {
		// Mock data
		CourseEntitydto courseDto = new CourseEntitydto();
		courseDto.setCourseId("course-01");
		courseDto.setCourseName("java fullstack");
		UserEntity userEntity1 = new UserEntity();
		userEntity1.setuId(1L);
		courseDto.setId(userEntity1);
		CategoryEntity cat1 = new CategoryEntity();
		cat1.setCategoryId("cat-01");

		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setCourseId("course-01");
		courseEntity.setCourseName("java fullstack");
		UserEntity userEntity = new UserEntity();
		userEntity.setuId(1L);
		courseEntity.setId(userEntity);
		CategoryEntity cat = new CategoryEntity();
		cat.setCategoryId("cat-01");
		courseEntity.setCategory(cat);
		when(courseMapper.toEntity(courseDto)).thenReturn(courseEntity);
		when(courseMapper.toDto(courseEntity)).thenReturn(courseDto);
		when(courseEntityRepository.save(courseEntity)).thenReturn(courseEntity);

		CourseEntitydto result = courseService.saveCourseEntity(courseDto);

		assertEquals(courseDto.getCourseId(), result.getCourseId());
		assertEquals(courseDto.getCourseName(), result.getCourseName());

		verify(courseMapper, times(1)).toEntity(courseDto);
		verify(courseEntityRepository, times(1)).save(courseEntity);

		ArgumentCaptor<CourseEntity> courseEntityCaptor = ArgumentCaptor.forClass(CourseEntity.class);
		verify(courseEntityRepository).save(courseEntityCaptor.capture());
		assertEquals(courseEntity.getCourseId(), courseEntityCaptor.getValue().getCourseId());
		assertEquals(courseEntity.getCourseName(), courseEntityCaptor.getValue().getCourseName());
	}

	@Test
	public void testUpdateCourseEntity_UserFound() {
		// Mock data
		// Mock data
		CourseEntitydto courseDto = new CourseEntitydto();
		courseDto.setCourseId("course-01");
		courseDto.setCourseName("java fullstack");
		UserEntity userEntity1 = new UserEntity();
		userEntity1.setuId(1L);
		courseDto.setId(userEntity1);
		CategoryEntity cat1 = new CategoryEntity();
		cat1.setCategoryId("cat-01");

		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setCourseId("course-01");
		courseEntity.setCourseName("java fullstack");
		UserEntity userEntity = new UserEntity();
		userEntity.setuId(1L);
		courseEntity.setId(userEntity);
		CategoryEntity cat = new CategoryEntity();
		cat.setCategoryId("cat-01");
		courseEntity.setCategory(cat);

		// Mock findById to return the courseEntity
		when(courseMapper.toEntity(courseDto)).thenReturn(courseEntity);
		when(courseEntityRepository.findById(courseEntity.getCourseId())).thenReturn(Optional.of(courseEntity));
		when(courseEntityRepository.save(any(CourseEntity.class))).thenReturn(courseEntity);
		when(courseMapper.toDto(courseEntity)).thenReturn(courseDto);

		CourseEntitydto updatedDto = courseService.updateCourseEntity(courseDto, "course-01");

		assertNotNull(updatedDto);
		verify(courseEntityRepository).findById("course-01");
		verify(courseEntityRepository).save(any(CourseEntity.class));
		verify(courseMapper, times(2)).toDto(courseEntity);
	}

	@Test
	public void testUpdateUserEntity_UserNotFound() {
		// Mock data
		CourseEntitydto userDto = new CourseEntitydto();
		Optional<CourseEntity> userOptional = Optional.empty();
		when(courseEntityRepository.findById("course-01")).thenReturn(userOptional);

		// Test and verify
		assertThrows(EntityNotFoundException.class, () -> {
			courseService.updateCourseEntity(userDto, "course-01");
		});
		verify(courseEntityRepository).findById("course-01");
		verify(courseEntityRepository, never()).save(any(CourseEntity.class));
		verify(courseMapper, never()).toEntity(userDto);
		verify(courseMapper, never()).toDto(any(CourseEntity.class));
	}

}
