package com.effigo.LearningPortal.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.effigo.LearningPortal.dto.mapper.CourseEntityMapper;
import com.effigo.LearningPortal.dto.request.CourseEntityrequest;
import com.effigo.LearningPortal.dto.response.CourseEntityResponse;
import com.effigo.LearningPortal.entity.CourseEntity;
import com.effigo.LearningPortal.repository.CourseEntityRepository;
import com.effigo.LearningPortal.service.CourseService;
import jakarta.transaction.Transactional;
@Service
@Transactional
public class CourseServiceImpl implements CourseService{
	private final CourseEntityRepository courseentityRepository;
	public CourseServiceImpl(CourseEntityRepository courseentityRepository)
	{
		this.courseentityRepository=courseentityRepository;
	}

	@Override
	public List<CourseEntity> findAllCourse() {
		return courseentityRepository.findAll();
	}

	@Override
	public Optional<CourseEntity> findById(Long id) {
		return courseentityRepository.findById(id);
	}

	@Override
	public void deleteCourseentity(Long id) {
		courseentityRepository.deleteById(id);
	}

	@Override
	public CourseEntityResponse saveCourseEntity(CourseEntityrequest courserequest) {
		CourseEntity userEntity = CourseEntityMapper.MAPPER.fromRequestToEntity(courserequest);
        courseentityRepository.save(userEntity);
        return CourseEntityMapper.MAPPER.fromEntityToResponse(userEntity);
	}

	@Override
	public CourseEntityResponse updateCourseEntity(CourseEntityrequest courserequest, Long id) {
		Optional<CourseEntity> checkExistinguser = findById(id);
        if (! checkExistinguser.isPresent())
            throw new RuntimeException("Course Id "+ id + " Not Found!");
        CourseEntity userEntity = CourseEntityMapper.MAPPER.fromRequestToEntity(courserequest);
        courseentityRepository.save(userEntity);
        return CourseEntityMapper.MAPPER.fromEntityToResponse(userEntity);
	}

}

