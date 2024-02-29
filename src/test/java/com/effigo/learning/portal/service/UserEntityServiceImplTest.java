package com.effigo.learning.portal.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.effigo.learning.portal.dto.CourseEntitydto;
import com.effigo.learning.portal.dto.UserEntitydto;
import com.effigo.learning.portal.dto.mapper.CourseEntityMapper;
import com.effigo.learning.portal.dto.mapper.UserEntityMapper;
import com.effigo.learning.portal.entity.CourseEntity;
import com.effigo.learning.portal.entity.FavoriteEntity;
import com.effigo.learning.portal.entity.UserCourseEnrollmentEntity;
import com.effigo.learning.portal.entity.UserEntity;
import com.effigo.learning.portal.entity.UserEntity.UserType;
import com.effigo.learning.portal.repository.CourseEntityRepository;
import com.effigo.learning.portal.repository.FavoriteEntityRepository;
import com.effigo.learning.portal.repository.UserCourseEnrollmentRepository;
import com.effigo.learning.portal.repository.UserEntityRepository;
import com.effigo.learning.portal.service.UserEntityServiceImpl.UnauthorizedException;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserEntityServiceImplTest {
	@Mock
	private UserEntityMapper userEntityMapper;
	@Mock
	private UserEntityRepository userEntityRepository;

	@InjectMocks
	private UserEntityServiceImpl userEntityService;
	@Mock
	private CourseEntityRepository courseEntityRepository;

	@Mock
	private CourseEntityMapper courseEntityMapper;

	@Mock
	private FavoriteEntityRepository favoriteEntityRepository;

	@InjectMocks
	private CourseServiceImpl courseEntityService;

	@Mock
	private UserCourseEnrollmentRepository userCourseEnrollmentRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindAllUser() {
		UserEntity user1 = new UserEntity();
		user1.setuId(1L);
		user1.setUsername("user1");
		user1.setPassword("pass@1");
		user1.setUserType(UserType.LEARNER);

		UserEntity user2 = new UserEntity();
		user2.setuId(2L);
		user2.setUsername("user2");
		user2.setPassword("pass@1");
		user2.setUserType(UserType.LEARNER);

		List<UserEntity> userList = Arrays.asList(user1, user2);

		when(userEntityRepository.findAll()).thenReturn(userList);

		UserEntitydto user1Dto = new UserEntitydto();
		user1Dto.setUsername("user1");
		UserEntitydto user2Dto = new UserEntitydto();
		user2Dto.setUsername("user2");
		when(userEntityMapper.toDto(userList)).thenReturn(Arrays.asList(user1Dto, user2Dto));

		List<UserEntitydto> result = userEntityService.findAllUser();

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getUsername()).isEqualTo("user1");
		assertThat(result.get(1).getUsername()).isEqualTo("user2");

		verify(userEntityRepository, times(1)).findAll();

		verify(userEntityMapper, times(1)).toDto(userList);
	}

	@Test
	public void testFindById() {

		UserEntity userEntity = new UserEntity();
		when(userEntityMapper.toDto(any(UserEntity.class))).thenReturn(new UserEntitydto());

		Long id = 1L;
		when(userEntityRepository.findById(id)).thenReturn(Optional.of(userEntity));

		UserEntitydto result = userEntityService.findById(id);

		assertNotNull(result);
	}

	@Test
	public void testDeleteUserEntity() {
		userEntityService.deleteUserentity(1L);
		verify(userEntityRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testSaveUserEntity() {

		UserEntitydto userEntityDto = new UserEntitydto();
		userEntityDto.setUsername("testUser");
		userEntityDto.setPassword("testPassword");
		userEntityDto.setUserType(UserType.LEARNER);

		UserEntity userEntity = new UserEntity();
		userEntity.setUsername("testUser");
		userEntity.setPassword("testPassword");
		userEntity.setUserType(UserType.LEARNER);
		userEntity.setCreatedOn(LocalDateTime.now());
		userEntity.setUpdatedOn(LocalDateTime.now());

		when(userEntityMapper.toEntity(userEntityDto)).thenReturn(userEntity);
		when(userEntityRepository.save(userEntity)).thenReturn(userEntity);
		when(userEntityMapper.toDto(userEntity)).thenReturn(userEntityDto);

		UserEntitydto savedUserEntity = userEntityService.saveUserEntity(userEntityDto);

		assertEquals(userEntityDto.getUsername(), savedUserEntity.getUsername());
		assertEquals(userEntityDto.getPassword(), savedUserEntity.getPassword());
		assertEquals(userEntityDto.getUserType(), savedUserEntity.getUserType());

		verify(userEntityMapper, times(1)).toEntity(userEntityDto);
		verify(userEntityRepository, times(1)).save(userEntity);

		ArgumentCaptor<UserEntity> userEntityCaptor = ArgumentCaptor.forClass(UserEntity.class);
		verify(userEntityRepository).save(userEntityCaptor.capture());
		assertEquals(userEntity.getUsername(), userEntityCaptor.getValue().getUsername());
		assertEquals(userEntity.getPassword(), userEntityCaptor.getValue().getPassword());
		assertEquals(userEntity.getUserType(), userEntityCaptor.getValue().getUserType());
	}

	//role based access test
	@Test
	public void testSaveUserEntity2() {

		UserEntitydto userEntityDto = new UserEntitydto();
		userEntityDto.setuId(6L);
		userEntityDto.setUsername("testUser");
		userEntityDto.setPassword("testPassword");
		userEntityDto.setUserType(UserType.AUTHOR);

		UserEntity userEntity = new UserEntity();
		userEntity.setuId(6L);
		userEntity.setUsername("testUser");
		userEntity.setPassword("testPassword");
		userEntity.setUserType(UserType.AUTHOR);
		userEntity.setCreatedOn(LocalDateTime.now());
		userEntity.setUpdatedOn(LocalDateTime.now());

		when(userEntityMapper.toEntity(userEntityDto)).thenReturn(userEntity);
		when(userEntityRepository.save(userEntity)).thenReturn(userEntity);
		when(userEntityMapper.toDto(userEntity)).thenReturn(userEntityDto);

		UserEntitydto savedUserEntity = userEntityService.saveUserEntity(userEntityDto);

		assertEquals(userEntityDto.getUsername(), savedUserEntity.getUsername());
		assertEquals(userEntityDto.getPassword(), savedUserEntity.getPassword());
		assertEquals(userEntityDto.getUserType(), savedUserEntity.getUserType());

		verify(userEntityMapper, times(1)).toEntity(userEntityDto);
		verify(userEntityRepository, times(1)).save(userEntity);

		ArgumentCaptor<UserEntity> userEntityCaptor = ArgumentCaptor.forClass(UserEntity.class);
		verify(userEntityRepository).save(userEntityCaptor.capture());
		assertEquals(userEntity.getUsername(), userEntityCaptor.getValue().getUsername());
		assertEquals(userEntity.getPassword(), userEntityCaptor.getValue().getPassword());
		assertEquals(userEntity.getUserType(), userEntityCaptor.getValue().getUserType());
	}

	//role based access user with id not present
	@Test
	public void testSaveUserEntity1_UserWithIdNotPresentForAdmin_ReturnsNull() {
		// Mocking input data
		UserEntitydto userentityrequest = new UserEntitydto();
		UserType usertype = UserType.ADMIN;
		Long id = 1L;
		String username = "admin";
		String password = "admin";
		UserEntity user = new UserEntity();
		user.setuId(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setUserType(usertype);

		// Setting up mock behavior
		when(userEntityRepository.findById(id)).thenReturn(Optional.empty());

		// Calling the method to test
		UserEntitydto result = userEntityService.saveUserEntity1(userentityrequest, usertype, id, username, password);

		// Verifying the behavior
		verify(userEntityRepository, never()).save(any(UserEntity.class));
		assertNull(result);
	}

	//incorrect username
	@Test
	public void testSaveUserEntity1_IncorrectUsername_ReturnsNull() {
		// Mocking input data
		UserEntitydto userentityrequest = new UserEntitydto();
		UserType usertype = UserType.ADMIN;
		Long id = 1L;
		String username = "admin";
		String password = "admin";
		UserEntity user = new UserEntity();
		user.setuId(id);
		user.setUsername("incorrect_username");
		user.setPassword(password);
		user.setUserType(usertype);

		// Setting up mock behavior
		when(userEntityRepository.findById(id)).thenReturn(Optional.of(user));

		// Calling the method to test
		UserEntitydto result = userEntityService.saveUserEntity1(userentityrequest, usertype, id, username, password);

		// Verifying the behavior
		verify(userEntityRepository, never()).save(any(UserEntity.class));
		assertNull(result);
	}

	//incorrect password
	@Test
	public void testSaveUserEntity1_IncorrectPassword_ReturnsNull() {
		// Mocking input data
		UserEntitydto userentityrequest = new UserEntitydto();
		UserType usertype = UserType.ADMIN;
		Long id = 1L;
		String username = "admin";
		String password = "admin";
		UserEntity user = new UserEntity();
		user.setuId(id);
		user.setUsername(username);
		user.setPassword("incorrect_password");
		user.setUserType(usertype);

		// Setting up mock behavior
		when(userEntityRepository.findById(id)).thenReturn(Optional.of(user));

		// Calling the method to test
		UserEntitydto result = userEntityService.saveUserEntity1(userentityrequest, usertype, id, username, password);

		// Verifying the behavior
		verify(userEntityRepository, never()).save(any(UserEntity.class));
		assertNull(result);
	}

	//update user if found
	@Test
	public void testUpdateUserEntity_UserFound() {
		UserEntitydto userDto = new UserEntitydto();
		UserEntity userEntity = new UserEntity();
		userEntity = new UserEntity();
		userEntity.setuId(1L);
		userEntity.setUsername("testUser");
		userEntity.setPassword("password");
		userEntity.setUserType(UserType.ADMIN);

		userDto.setuId(1L);
		userDto.setUsername("testUser");
		userDto.setPassword("password");
		userDto.setUserType(UserType.ADMIN);
		when(userEntityMapper.toEntity(userDto)).thenReturn(userEntity);
		when(userEntityRepository.findById(1L)).thenReturn(Optional.of(userEntity));
		when(userEntityRepository.save(any(UserEntity.class))).thenReturn(userEntity);
		when(userEntityMapper.toDto(userEntity)).thenReturn(userDto);

		UserEntitydto updatedDto = userEntityService.updateUserEntity(userDto, 1L);

		assertNotNull(updatedDto);
		verify(userEntityRepository).findById(1L);
		verify(userEntityRepository).save(any(UserEntity.class));
		verify(userEntityMapper, times(2)).toDto(userEntity);
	}

	//update method if user is not found
	@Test
	public void testUpdateUserEntity_UserNotFound() {
		// Mock data
		Long id = 1L;
		UserEntitydto userDto = new UserEntitydto();
		Optional<UserEntity> userOptional = Optional.empty();
		when(userEntityRepository.findById(id)).thenReturn(userOptional);

		// Test and verify
		assertThrows(EntityNotFoundException.class, () -> {
			userEntityService.updateUserEntity(userDto, id);
		});
		verify(userEntityRepository).findById(id);
		verify(userEntityRepository, never()).save(any(UserEntity.class));
		verify(userEntityMapper, never()).toEntity(userDto);
		verify(userEntityMapper, never()).toDto(any(UserEntity.class));
	}

	@Test
	void testSaveUserEntity1() {
		Long id = 1L;
		String username = "admin";
		String password = "admin123";
		UserType usertype = UserType.ADMIN;

		UserEntity userEntity = new UserEntity();
		userEntity.setuId(id);
		userEntity.setUsername(username);
		userEntity.setPassword(password);
		userEntity.setUserType(usertype);

		UserEntitydto userEntityDto = new UserEntitydto();
		userEntityDto.setuId(id);
		userEntityDto.setUsername(username);
		userEntityDto.setPassword(password);
		userEntityDto.setUserType(usertype);

		when(userEntityRepository.findById(id)).thenReturn(Optional.of(userEntity));
		when(userEntityMapper.toEntity(userEntityDto)).thenReturn(userEntity);
		when(userEntityMapper.toDto(userEntity)).thenReturn(userEntityDto);

		UserEntitydto result = userEntityService.saveUserEntity1(userEntityDto, usertype, id, username, password);

		verify(userEntityRepository, times(1)).findById(id);
		verify(userEntityRepository, times(1)).save(userEntity);
		verify(userEntityMapper, times(1)).toEntity(userEntityDto);
		verify(userEntityMapper, times(1)).toDto(userEntity);

		assertNotNull(result);
		assertEquals(userEntityDto, result);
	}

	@Test
	void testSaveCourseEntity1() {
		Long id = 1L;
		String username = "author";
		String password = "author123";
		UserType usertype = UserType.AUTHOR;

		UserEntity userEntity = new UserEntity();
		userEntity.setuId(id);
		userEntity.setUsername(username);
		userEntity.setPassword(password);
		userEntity.setUserType(usertype);

		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setCourseId("course-01");
		courseEntity.setCourseName("java");

		CourseEntitydto courseEntityDto = new CourseEntitydto();
		courseEntityDto.setCourseId("course-01");
		courseEntityDto.setCourseName("java");

		when(userEntityRepository.findById(id)).thenReturn(Optional.of(userEntity));
		when(courseEntityMapper.toEntity(courseEntityDto)).thenReturn(courseEntity);
		when(courseEntityMapper.toDto(courseEntity)).thenReturn(courseEntityDto);
		when(courseEntityRepository.save(courseEntity)).thenReturn(courseEntity);
		CourseEntitydto result = userEntityService.saveCourseEntity1(courseEntityDto, usertype, id, username, password);

		verify(userEntityRepository, times(1)).findById(id);
		verify(courseEntityRepository, times(1)).save(courseEntity);
		verify(courseEntityMapper, times(1)).toEntity(courseEntityDto);
		verify(courseEntityMapper, times(1)).toDto(courseEntity);

		assertNotNull(result);
		assertEquals(courseEntityDto, result);
	}

	@Test
	public void testSaveCourseEntity1_UserNotFound_ReturnsNull() {
		// Mocking input data
		CourseEntitydto courserequest = new CourseEntitydto();
		UserType usertype = UserType.AUTHOR;
		Long id = 1L;
		String username = "author";
		String password = "password";

		// Setting up mock behavior
		when(userEntityRepository.findById(id)).thenReturn(Optional.empty());

		// Calling the method to test
		CourseEntitydto result = userEntityService.saveCourseEntity1(courserequest, usertype, id, username, password);

		// Verifying the behavior
		verify(courseEntityRepository, never()).save(any(CourseEntity.class));
		assertNull(result);
	}

	@Test
	public void testSaveCourseEntity1_UserNotAuthor_ReturnsNull() {
		// Mocking input data
		CourseEntitydto courserequest = new CourseEntitydto();
		UserType usertype = UserType.AUTHOR;
		Long id = 1L;
		String username = "author";
		String password = "password";
		UserEntity user = new UserEntity();
		user.setuId(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setUserType(UserType.ADMIN); // Setting userType to a non-AUTHOR type

		// Setting up mock behavior
		when(userEntityRepository.findById(id)).thenReturn(Optional.of(user));

		// Calling the method to test
		CourseEntitydto result = userEntityService.saveCourseEntity1(courserequest, usertype, id, username, password);

		// Verifying the behavior
		verify(courseEntityRepository, never()).save(any(CourseEntity.class));
		assertNull(result);
	}

	@Test
	public void testSaveCourseEntity1_IncorrectUsername_ReturnsNull() {
		// Mocking input data
		CourseEntitydto courserequest = new CourseEntitydto();
		UserType usertype = UserType.AUTHOR;
		Long id = 1L;
		String username = "author";
		String password = "password";
		UserEntity user = new UserEntity();
		user.setuId(id);
		user.setUsername("incorrect_username");
		user.setPassword(password);
		user.setUserType(usertype);

		// Setting up mock behavior
		when(userEntityRepository.findById(id)).thenReturn(Optional.of(user));

		// Calling the method to test
		CourseEntitydto result = userEntityService.saveCourseEntity1(courserequest, usertype, id, username, password);

		// Verifying the behavior
		verify(courseEntityRepository, never()).save(any(CourseEntity.class));
		assertNull(result);
	}

	@Test
	public void testSaveCourseEntity1_IncorrectPassword_ReturnsNull() {
		// Mocking input data
		CourseEntitydto courserequest = new CourseEntitydto();
		UserType usertype = UserType.AUTHOR;
		Long id = 1L;
		String username = "author";
		String password = "password";
		UserEntity user = new UserEntity();
		user.setuId(id);
		user.setUsername(username);
		user.setPassword("incorrect_password");
		user.setUserType(usertype);

		// Setting up mock behavior
		when(userEntityRepository.findById(id)).thenReturn(Optional.of(user));

		// Calling the method to test
		CourseEntitydto result = userEntityService.saveCourseEntity1(courserequest, usertype, id, username, password);

		// Verifying the behavior
		verify(courseEntityRepository, never()).save(any(CourseEntity.class));
		assertNull(result);
	}

	/*@Test
	void testUpdateCourseEntity1() {
		// Test data
		Long id = 1L;
		String username = "author";
		String password = "author123";
		UserType usertype = UserType.AUTHOR;
		String courseId = "course-01";
	
		// Mock UserEntity
		UserEntity userEntity = new UserEntity();
		userEntity.setuId(id);
		userEntity.setUsername(username);
		userEntity.setPassword(password);
		userEntity.setUserType(usertype);
		userEntityRepository.save(userEntity);
		// Mock CourseEntity
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setCourseId(courseId);
		courseEntity.setCourseName("java");
	
		// Mock CourseEntitydto
		CourseEntitydto courseEntityDto = new CourseEntitydto();
		courseEntityDto.setCourseId(courseId);
		courseEntityDto.setCourseName("java");
	
		// Mock repository methods
		when(userEntityRepository.findById(id)).thenReturn(Optional.of(userEntity));
		when(courseEntityRepository.findById(courseId)).thenReturn(Optional.of(courseEntity));
		when(courseEntityMapper.toEntity(courseEntityDto)).thenReturn(courseEntity);
		when(courseEntityMapper.toDto(courseEntity)).thenReturn(courseEntityDto);
	
		// Call the method
		CourseEntitydto result = userEntityService.updateCourseEntity1(courseEntityDto, usertype, id, username,
				password, courseId);
	
		// Verify interactions
		verify(userEntityRepository, times(1)).findById(id);
		verify(courseEntityRepository, times(1)).findById(courseId);
		verify(courseEntityRepository, times(1)).save(courseEntity); // Ensure that the correct CourseEntity object is passed to save
	
		// Assertions
		assertNotNull(result);
		assertEquals(courseEntityDto, result);
	}
	
	@Test
	public void testUpdateCourseEntity1_UserNotFound_ReturnsNull() {
		// Mocking input data
		CourseEntitydto courserequest = new CourseEntitydto();
		UserType usertype = UserType.AUTHOR;
		Long id = 1L;
		String username = "author";
		String password = "password";
	
		// Setting up mock behavior
		when(userEntityRepository.findById(id)).thenReturn(Optional.empty());
	
		// Calling the method to test
		CourseEntitydto result = userEntityService.updateCourseEntity1(courserequest, usertype, id, username, password,
				"courseid");
	
		// Verifying the behavior
		verify(courseEntityRepository, never()).save(any(CourseEntity.class));
		assertNull(result);
	}
	
	@Test
	public void testUpdateCourseEntity1_UserNotAuthor_ReturnsNull() {
		// Mocking input data
		CourseEntitydto courserequest = new CourseEntitydto();
		UserType usertype = UserType.ADMIN;
		Long id = 1L;
		String username = "author";
		String password = "password";
		UserEntity user = new UserEntity();
		user.setuId(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setUserType(UserType.ADMIN); // Setting userType to a non-AUTHOR type
	
		// Setting up mock behavior
		when(userEntityRepository.findById(id)).thenReturn(Optional.of(user));
	
		// Calling the method to test
		CourseEntitydto result = userEntityService.updateCourseEntity1(courserequest, usertype, id, username, password,
				"courseid");
	
		// Verifying the behavior
		verify(courseEntityRepository, never()).save(any(CourseEntity.class));
		assertNull(result);
	}
	
	@Test
	public void testUpdateCourseEntity1_IncorrectUsername_ReturnsNull() {
		// Mocking input data
		CourseEntitydto courserequest = new CourseEntitydto();
		UserType usertype = UserType.ADMIN;
		Long id = 1L;
		String username = "author";
		String password = "password";
		UserEntity user = new UserEntity();
		user.setuId(id);
		user.setUsername("incorrect_username");
		user.setPassword(password);
		user.setUserType(usertype);
	
		// Setting up mock behavior
		when(userEntityRepository.findById(id)).thenReturn(Optional.of(user));
	
		// Calling the method to test
		CourseEntitydto result = userEntityService.updateCourseEntity1(courserequest, usertype, id, username, password,
				"courseid");
	
		// Verifying the behavior
		verify(courseEntityRepository, never()).save(any(CourseEntity.class));
		assertNull(result);
	}
	
	@Test
	public void testUpdateCourseEntity1_IncorrectPassword_ReturnsNull() {
		// Mocking input data
		CourseEntitydto courserequest = new CourseEntitydto();
		UserType usertype = UserType.AUTHOR;
		Long id = 1L;
		String username = "author";
		String password = "password";
		UserEntity user = new UserEntity();
		user.setuId(id);
		user.setUsername(username);
		user.setPassword("incorrect_password");
		user.setUserType(usertype);
	
		// Setting up mock behavior
		when(userEntityRepository.findById(id)).thenReturn(Optional.of(user));
	
		// Calling the method to test
		CourseEntitydto result = userEntityService.updateCourseEntity1(courserequest, usertype, id, username, password,
				"courseid");
	
		// Verifying the behavior
		verify(courseEntityRepository, never()).save(any(CourseEntity.class));
		assertNull(result);
	}
	
	@Test
	public void testUpdateCourseEntity1_SuccessfulUpdate_ReturnsDto() {
		// Mocking input data
		CourseEntitydto courserequest = new CourseEntitydto();
		courserequest.setCourseId("course-01");
		CategoryEntity cat1 = new CategoryEntity();
		cat1.setCategoryId("cat-01");
		courserequest.setCategory(cat1);
		courserequest.setCourseName("java");
		UserEntity u1 = new UserEntity();
		u1.setuId(1L);
		courserequest.setId(u1);
		UserType usertype = UserType.AUTHOR;
		Long id = 1L;
		String username = "author";
		String password = "password";
		String courseid = "course-01";
		CourseEntity course = new CourseEntity();
		course.setCourseId("course-01");
		CategoryEntity cat = new CategoryEntity();
		cat.setCategoryId("cat-01");
		course.setCategory(cat);
		course.setCourseName("java");
		UserEntity u = new UserEntity();
		u.setuId(1L);
		course.setId(u);
		UserEntity user = new UserEntity();
		user.setuId(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setUserType(usertype);
	
		// Mocking repository behaviors
		when(userEntityRepository.findById(id)).thenReturn(Optional.of(user));
		when(courseEntityRepository.findById(courseid)).thenReturn(Optional.of(new CourseEntity()));
		when(courseEntityMapper.toEntity(courserequest)).thenReturn(new CourseEntity());
		when(courseEntityMapper.toDto(any(CourseEntity.class))).thenReturn(new CourseEntitydto());
		when(courseEntityRepository.save(any(CourseEntity.class))).thenReturn(course);
	
		// Calling the method to test
		CourseEntitydto result = userEntityService.updateCourseEntity1(courserequest, UserType.AUTHOR, 1L, "author",
				"password", "course-01");
	
		// Verifying the behavior
		verify(courseEntityRepository, times(1)).save(any(CourseEntity.class));
		assertEquals(course.getCourseId(), result.getCourseId());
		assertEquals(course.getCourseName(), result.getCourseName());
		assertEquals(course.getCategory().getCategoryId(), result.getCategory().getCategoryId());
		assertEquals(course.getId().getuId(), result.getId().getuId());
		assertNotNull(result);
	}*/

	// 
	@Test
	public void testUpdateCourse_UserNotFound_ThrowsNotFoundException() {
		// Arrange
		when(userEntityRepository.findById(anyLong())).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(NotFoundException.class, () -> {
			userEntityService.updateCourse(new CourseEntitydto(), UserType.ADMIN, 1L, "username", "password",
					"courseid");
		});
	}

	@Test
	public void testUpdateCourse_AuthorizationFailure_ThrowsUnauthorizedException() {
		// Arrange
		UserEntity user = new UserEntity();
		user.setUserType(UserType.ADMIN); // Assuming user is not an ADMIN
		when(userEntityRepository.findById(1L)).thenReturn(Optional.of(user));

		// Act & Assert
		assertThrows(UnauthorizedException.class, () -> {
			userEntityService.updateCourse(new CourseEntitydto(), UserType.AUTHOR, 1L, "username", "password",
					"courseid");
		});
	}

	@Test
	public void testUpdateCourse_CourseNotFound_ThrowsNotFoundException() {
		// Arrange
		UserEntity user = new UserEntity();
		user.setUserType(UserType.AUTHOR); // Assuming user is an ADMIN
		user.setUsername("username");
		user.setPassword("password");
		when(userEntityRepository.findById(1L)).thenReturn(Optional.of(user));
		when(courseEntityRepository.findById("courseid")).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(NotFoundException.class, () -> {
			userEntityService.updateCourse(new CourseEntitydto(), UserType.AUTHOR, 1L, "username", "password",
					"courseid");
		});

		// Verify that findById was called with the correct argument
		verify(courseEntityRepository, times(1)).findById("courseid");
	}

	@Test
	public void testUpdateCourse_SuccessfulUpdate_ReturnsDto() throws NotFoundException {
		// Arrange
		UserEntity user = new UserEntity();
		user.setuId(1L);
		user.setUserType(UserType.AUTHOR); // Assuming user is an ADMIN
		user.setUsername("username");
		user.setPassword("password");
		when(userEntityRepository.findById(1L)).thenReturn(Optional.of(user));
		when(courseEntityRepository.findById("courseid")).thenReturn(Optional.of(new CourseEntity()));
		when(courseEntityMapper.toEntity(any(CourseEntitydto.class))).thenReturn(new CourseEntity());
		when(courseEntityMapper.toDto(any(CourseEntity.class))).thenReturn(new CourseEntitydto());

		// Act
		CourseEntitydto result = userEntityService.updateCourse(new CourseEntitydto(), UserType.AUTHOR, 1L, "username",
				"password", "courseid");

		// Assert
		verify(courseEntityRepository, times(1)).save(any(CourseEntity.class));
		assertNotNull(result);
	}

	@Test
	void testSaveFavoriteEntity() {
		// Test data
		Long id = 1L;
		String username = "learner";
		String password = "learner123";
		UserType usertype = UserType.LEARNER;
		String courseId = "course-01";

		// Mock UserEntity
		UserEntity userEntity = new UserEntity();
		userEntity.setuId(id);
		userEntity.setUsername(username);
		userEntity.setPassword(password);
		userEntity.setUserType(usertype);

		// Mock CourseEntity
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setCourseId(courseId);

		// Mock repository methods
		when(userEntityRepository.findById(id)).thenReturn(Optional.of(userEntity));
		when(courseEntityRepository.findById(courseId)).thenReturn(Optional.of(courseEntity));

		// Call the method
		String result = userEntityService.saveFavoriteEntity(usertype, id, username, password, courseId);

		// Verify interactions
		verify(userEntityRepository, times(1)).findById(id);
		verify(courseEntityRepository, times(1)).findById(courseId);
		verify(favoriteEntityRepository, times(1)).save(any(FavoriteEntity.class)); // Verify that the save method is called with any FavoriteEntity object

		// Assertions
		assertEquals("favorite added", result);
	}

	@Test
	void testCourseEnrollment() {
		Long id = 1L;
		String username = "learner";
		String password = "learner123";
		UserType usertype = UserType.LEARNER;
		String courseId = "course-01";

		UserEntity userEntity = new UserEntity();
		userEntity.setuId(id);
		userEntity.setUsername(username);
		userEntity.setPassword(password);
		userEntity.setUserType(usertype);

		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setCourseId(courseId);

		when(userEntityRepository.findById(id)).thenReturn(Optional.of(userEntity));
		when(courseEntityRepository.findById(courseId)).thenReturn(Optional.of(courseEntity));

		String result = userEntityService.courseenrollment(usertype, id, username, password, courseId);

		verify(userEntityRepository, times(1)).findById(id);
		verify(courseEntityRepository, times(1)).findById(courseId);
		verify(userCourseEnrollmentRepository, times(1)).save(any(UserCourseEnrollmentEntity.class)); // Verify that the save method is called with any UserCourseEnrollmentEntity object

		assertEquals("User enrolled to the course", result);
	}
}
