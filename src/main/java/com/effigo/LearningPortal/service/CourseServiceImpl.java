package com.effigo.LearningPortal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effigo.LearningPortal.dto.CourseEntitydto;
import com.effigo.LearningPortal.dto.mapper.CourseEntityMapper;
import com.effigo.LearningPortal.entity.CourseEntity;
import com.effigo.LearningPortal.repository.CourseEntityRepository;

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

	public CourseEntitydto findById(Long id) {
		Optional<CourseEntity> user = courseentityRepository.findById(id);
		CourseEntity course = user.get();
		return courseMapper.toDto(course);
	}

	public void deleteCourseentity(Long id) {
		courseentityRepository.deleteById(id);
	}

	public CourseEntitydto saveCourseEntity(CourseEntitydto courserequest) {
		CourseEntity courseEntity = courseMapper.toEntity(courserequest);
		courseentityRepository.save(courseEntity);
		return courseMapper.toDto(courseEntity);
	}

	public CourseEntitydto updateCourseEntity(CourseEntitydto courserequest, Long id) {
		Optional<CourseEntity> checkExistinguser = courseentityRepository.findById(id);
		if (!checkExistinguser.isPresent())
			log.error("Course Id " + id + " Not Found!");
		CourseEntity courseEntity = courseMapper.toEntity(courserequest);
		courseentityRepository.save(courseEntity);
		return courseMapper.toDto(courseEntity);
	}

}
