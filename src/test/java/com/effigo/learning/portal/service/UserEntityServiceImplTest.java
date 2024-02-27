package com.effigo.learning.portal.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.effigo.learning.portal.dto.UserEntitydto;
import com.effigo.learning.portal.dto.mapper.UserEntityMapper;
import com.effigo.learning.portal.entity.UserEntity;
import com.effigo.learning.portal.entity.UserEntity.UserType;
import com.effigo.learning.portal.repository.UserEntityRepository;

@ExtendWith(MockitoExtension.class)
public class UserEntityServiceImplTest {
	@Mock
	private UserEntityMapper userEntityMapper;
	@Mock
	private UserEntityRepository userEntityRepository;

	@InjectMocks
	//private UserEntityServiceImpl userEntityService;
	//private UserEntityRepository userEntityRepository;
	// private UserEntityMapper userEntityMapper;
	private UserEntityServiceImpl userEntityService;

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

		// Stub the repository method to return the test data
		when(userEntityRepository.findAll()).thenReturn(userList);

		// Mock the mapper method to return DTOs
		UserEntitydto user1Dto = new UserEntitydto();
		user1Dto.setUsername("user1");
		UserEntitydto user2Dto = new UserEntitydto();
		user2Dto.setUsername("user2");
		when(userEntityMapper.toDto(userList)).thenReturn(Arrays.asList(user1Dto, user2Dto));

		// Call the method to be tested
		List<UserEntitydto> result = userEntityService.findAllUser();

		// Verify the behavior
		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getUsername()).isEqualTo("user1");
		assertThat(result.get(1).getUsername()).isEqualTo("user2");

		// Verify that repository method was called
		verify(userEntityRepository, times(1)).findAll();

		// Verify that mapper method was called with the correct argument
		verify(userEntityMapper, times(1)).toDto(userList);
	}

	@Test
	public void testFindById() {
		// Mocking the behavior of userEntityMapper
		UserEntity userEntity = new UserEntity();
		when(userEntityMapper.toDto(any(UserEntity.class))).thenReturn(new UserEntitydto());
		// Mocking the behavior of userentityRepository.findById
		Long id = 1L;
		when(userEntityRepository.findById(id)).thenReturn(Optional.of(userEntity));

		// Testing the findById method
		UserEntitydto result = userEntityService.findById(id);

		// Perform the test and assertions
		assertNotNull(result);
	}

	@Test
	public void testDeleteUserEntity() {
		userEntityService.deleteUserentity(1L);
		verify(userEntityRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testSaveUserEntity() {

		UserEntitydto userEntitydto = new UserEntitydto();
		userEntitydto.setuId(10L);
		userEntitydto.setUsername("testUser");
		userEntitydto.setPassword("password");
		userEntitydto.setUserType(UserType.LEARNER);
		userEntitydto.setCreatedOn(LocalDateTime.now());
		userEntitydto.setUpdatedOn(LocalDateTime.now());

		UserEntity userEntity = new UserEntity();
		userEntity.setuId(10L);
		userEntity.setUsername("testUser");
		userEntity.setPassword("password");
		userEntity.setUserType(UserType.LEARNER);
		userEntity.setCreatedOn(LocalDateTime.now());
		userEntity.setUpdatedOn(LocalDateTime.now());

		when(userEntityMapper.toEntity(any(UserEntitydto.class))).thenReturn(userEntity);

		when(userEntityRepository.save(any(UserEntity.class))).thenReturn(userEntity);

		UserEntitydto result = userEntityService.saveUserEntity(userEntitydto);

		verify(userEntityMapper, times(1)).toEntity(userEntitydto);
		verify(userEntityRepository, times(1)).save(userEntity);

		assertNotNull(result, "The result should not be null");
		assertEquals(userEntitydto, result);
	}
}
