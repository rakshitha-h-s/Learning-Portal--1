package com.effigo.learning.portal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effigo.learning.portal.dto.CourseEntitydto;
import com.effigo.learning.portal.dto.mapper.CourseEntityMapper;
import com.effigo.learning.portal.entity.CourseEntity;
import com.effigo.learning.portal.repository.CourseEntityRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CourseServiceImpl {
	@Autowired
	CourseEntityRepository courseentityRepository;

	@Autowired
	CourseEntityMapper courseMapper;

	public List<CourseEntitydto> findAllCourse() {
		List<CourseEntity> user = courseentityRepository.findAll();
		return courseMapper.toDto(user);
	}

	public CourseEntitydto findById(String id) {
		Optional<CourseEntity> user = courseentityRepository.findById(id);
		if (user.isPresent()) {
			CourseEntity course = user.get();
			return courseMapper.toDto(course);
		}
		throw new EntityNotFoundException("course id not found");
	}

	public void deleteCourseentity(String id) {
		courseentityRepository.deleteById(id);
	}

	public CourseEntitydto saveCourseEntity(CourseEntitydto courserequest) {
		CourseEntity courseEntity = courseMapper.toEntity(courserequest);
		courseentityRepository.save(courseEntity);
		return courseMapper.toDto(courseEntity);
	}

	public CourseEntitydto updateCourseEntity(CourseEntitydto courserequest, String id) {
		Optional<CourseEntitydto> checkExistinguser = Optional.ofNullable(findById(id));
		if (!checkExistinguser.isPresent()) {
			log.error("Course Id " + id + " Not Found!");
			throw new EntityNotFoundException("course id not found");
		}
		CourseEntity courseEntity = courseMapper.toEntity(courserequest);
		courseentityRepository.save(courseEntity);
		return courseMapper.toDto(courseEntity);
	}

}
