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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.effigo.learning.portal.dto.UserEntitydto;
import com.effigo.learning.portal.dto.mapper.UserEntityMapper;
import com.effigo.learning.portal.entity.UserEntity;
import com.effigo.learning.portal.entity.UserEntity.UserType;
import com.effigo.learning.portal.repository.UserEntityRepository;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserEntityServiceImplTest {
	@Mock
	private UserEntityMapper userEntityMapper;
	@Mock
	private UserEntityRepository userEntityRepository;

	@InjectMocks
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

	@Test
	public void testUpdateUserEntity_UserFound() {
		// Mock data
		Long id = 1L;
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
}
