package com.effigo.learning.portal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effigo.learning.portal.dto.UserCourseEnrollmentdto;
import com.effigo.learning.portal.dto.mapper.UserCourseEnrollmentMapper;
import com.effigo.learning.portal.entity.UserCourseEnrollmentEntity;
import com.effigo.learning.portal.repository.UserCourseEnrollmentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserCourseEnrollmentServiceImpl {
	@Autowired
	UserCourseEnrollmentRepository usercourseenrollmentRepository;

	@Autowired
	UserCourseEnrollmentMapper usercourseenrollmapper;

	public List<UserCourseEnrollmentdto> findAllCourseEnrollment() {
		List<UserCourseEnrollmentEntity> user = usercourseenrollmentRepository.findAll();
		return usercourseenrollmapper.toDto(user);
	}

	public UserCourseEnrollmentdto findById(Long id) {
		Optional<UserCourseEnrollmentEntity> user = usercourseenrollmentRepository.findById(id);
		if (user.isPresent()) {
			UserCourseEnrollmentEntity entity = user.get();
			return usercourseenrollmapper.toDto(entity);
		}
		return null;
	}

	public void deleteUserCourseEnrollmententity(Long id) {
		usercourseenrollmentRepository.deleteById(id);
	}

	public UserCourseEnrollmentdto saveUserCourseEnrollmentEntity(
			UserCourseEnrollmentdto usercourseenrollmententityrequest) {
		UserCourseEnrollmentEntity userEntity = usercourseenrollmapper.toEntity(usercourseenrollmententityrequest);
		usercourseenrollmentRepository.save(userEntity);
		return usercourseenrollmapper.toDto(userEntity);
	}

	public UserCourseEnrollmentdto updateUserCourseEnrollmentEntity(UserCourseEnrollmentdto usercourseentityrequest,
			Long id) {
		Optional<UserCourseEnrollmentdto> checkExistinguser = Optional.ofNullable(findById(id));
		if (!checkExistinguser.isPresent())
			log.error("course enrollment Id " + id + " Not Found!");
		UserCourseEnrollmentEntity userEntity = usercourseenrollmapper.toEntity(usercourseentityrequest);
		usercourseenrollmentRepository.save(userEntity);
		return usercourseenrollmapper.toDto(userEntity);
	}

}
