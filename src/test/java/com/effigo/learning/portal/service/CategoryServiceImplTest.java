package com.effigo.learning.portal.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
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

import com.effigo.learning.portal.dto.CategoryEntitydto;
import com.effigo.learning.portal.dto.mapper.CategoryEntityMapper;
import com.effigo.learning.portal.entity.CategoryEntity;
import com.effigo.learning.portal.entity.CategoryEntity.CategoryType;
import com.effigo.learning.portal.repository.CategoryEntityRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

	@InjectMocks
	private CategoryServiceImpl categoryService;

	@Mock
	private CategoryEntityRepository categoryRepository;

	@Mock
	private CategoryEntityMapper categoryMapper;

	@Test
	public void testFindAllCategory() {

		CategoryEntity user1 = new CategoryEntity();
		user1.setCategoryId("cat-01");
		user1.setCategoryType(CategoryType.JAVA);

		CategoryEntity user2 = new CategoryEntity();
		user2.setCategoryId("cat-02");
		user2.setCategoryType(CategoryType.OOPS);

		List<CategoryEntity> userList = Arrays.asList(user1, user2);

		when(categoryRepository.findAll()).thenReturn(userList);

		CategoryEntitydto user1Dto = new CategoryEntitydto();
		user1Dto.setCategoryId("cat-01");
		CategoryEntitydto user2Dto = new CategoryEntitydto();
		user2Dto.setCategoryId("cat-02");
		when(categoryMapper.toDto(userList)).thenReturn(Arrays.asList(user1Dto, user2Dto));

		List<CategoryEntitydto> result = categoryService.findAllCategory();
		System.out.println(result);
		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getCategoryId()).isEqualTo("cat-01");
		assertThat(result.get(1).getCategoryId()).isEqualTo("cat-02");

		verify(categoryRepository, times(1)).findAll();

		verify(categoryMapper, times(1)).toDto(userList);
	}

	@Test
	public void testFindById() {

		String id = "cat-01";
		Optional<CategoryEntity> categoryOptional = Optional.empty(); // Use Optional.empty() for non-existent ID
		when(categoryRepository.findById(id)).thenReturn(categoryOptional);

		categoryService.findById(id);

		verify(categoryRepository).findById(id);
	}

	@Test
	public void testDeleteCategoryentity() {
		// Test
		categoryService.deleteCategoryentity("1");

		// Verify
		verify(categoryRepository).deleteById("1");
	}

	@Test
	public void testSaveCategoryEntity() {

		CategoryEntitydto categoryEntitydto = new CategoryEntitydto();
		categoryEntitydto.setCategoryId("cat-01");
		categoryEntitydto.setCategoryType(CategoryType.JAVA);

		CategoryEntity catEntity = new CategoryEntity();
		catEntity.setCategoryId("cat-01");
		catEntity.setCategoryType(CategoryType.JAVA);
		catEntity.setCreatedOn(LocalDateTime.now());
		catEntity.setUpdatedOn(LocalDateTime.now());

		when(categoryMapper.toEntity(categoryEntitydto)).thenReturn(catEntity);
		when(categoryRepository.save(catEntity)).thenReturn(catEntity);
		when(categoryMapper.toDto(catEntity)).thenReturn(categoryEntitydto);

		CategoryEntitydto savedUserEntity = categoryService.saveCategoryEntity(categoryEntitydto);

		assertEquals(categoryEntitydto.getCategoryId(), savedUserEntity.getCategoryId());
		assertEquals(categoryEntitydto.getCategoryType(), savedUserEntity.getCategoryType());

		verify(categoryMapper, times(1)).toEntity(categoryEntitydto);
		verify(categoryRepository, times(1)).save(catEntity);

		ArgumentCaptor<CategoryEntity> catEntityCaptor = ArgumentCaptor.forClass(CategoryEntity.class);
		verify(categoryRepository).save(catEntityCaptor.capture());
		assertEquals(catEntity.getCategoryId(), catEntityCaptor.getValue().getCategoryId());
		assertEquals(catEntity.getCategoryType(), catEntityCaptor.getValue().getCategoryType());

	}

	@Test
	public void testUpdateCategoryEntity() {

		String uid = "1";
		CategoryEntitydto categoryDto = new CategoryEntitydto();
		Optional<CategoryEntity> categoryOptional = Optional.of(new CategoryEntity());
		when(categoryRepository.findById(uid)).thenReturn(categoryOptional);
		CategoryEntity categoryEntity = new CategoryEntity();
		when(categoryMapper.toEntity(categoryDto)).thenReturn(categoryEntity);

		categoryService.updateCategoryEntity(categoryDto, uid);

		verify(categoryRepository).save(categoryEntity);
	}
}
