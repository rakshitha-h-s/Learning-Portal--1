package com.effigo.LearningPortal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.effigo.LearningPortal.dto.mapper.CourseEntityMapper;
import com.effigo.LearningPortal.dto.mapper.UserEntityMapper;
import com.effigo.LearningPortal.dto.request.CourseEntityrequest;
import com.effigo.LearningPortal.dto.request.UserEntityrequest;
import com.effigo.LearningPortal.dto.response.CourseEntityResponse;
import com.effigo.LearningPortal.dto.response.UserEntityresponse;
import com.effigo.LearningPortal.entity.CourseEntity;
import com.effigo.LearningPortal.entity.FavoriteEntity;
import com.effigo.LearningPortal.entity.UserCourseEnrollmentEntity;
import com.effigo.LearningPortal.entity.UserEntity;
import com.effigo.LearningPortal.entity.UserEntity.UserType;
import com.effigo.LearningPortal.repository.CourseEntityRepository;
import com.effigo.LearningPortal.repository.FavoriteEntityRepository;
import com.effigo.LearningPortal.repository.UserCourseEnrollmentRepository;
import com.effigo.LearningPortal.repository.UserEntityRepository;
import com.effigo.LearningPortal.service.UserEntityService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UserEntityServiceImpl implements UserEntityService {
	private final UserEntityRepository userentityRepository;
	private final CourseEntityRepository courseentityrepository;
	private final FavoriteEntityRepository favoriteentity;
	private final UserCourseEnrollmentRepository enrollentity;

	public UserEntityServiceImpl(UserEntityRepository userentityRepository,
			CourseEntityRepository courseentityrepository, FavoriteEntityRepository favoriteentity,
			UserCourseEnrollmentRepository enrollentity) {
		this.userentityRepository = userentityRepository;
		this.courseentityrepository = courseentityrepository;
		this.favoriteentity = favoriteentity;
		this.enrollentity = enrollentity;
	}

	@Override
	public List<UserEntity> findAllUser() {
		return userentityRepository.findAll();
	}

	@Override
	public Optional<UserEntity> findById(Long id) {
		return userentityRepository.findById(id);
	}

	@Override
	public void deleteUserentity(Long id) {
		userentityRepository.deleteById(id);
	}

	@Override
	public UserEntityresponse saveUserEntity(UserEntityrequest userentityrequest) {

		UserEntity userEntity = UserEntityMapper.MAPPER.fromRequestToEntity(userentityrequest);
		userentityRepository.save(userEntity);
		return UserEntityMapper.MAPPER.fromEntityToResponse(userEntity);
	}

	private UserEntityRepository userRepository;

	public Long isValidUser(String username) {
		Optional<UserEntity> userOptional = userRepository.findByUsername(username);
		UserEntity user = userOptional.get();
		return user.getuId();
	}

	@Override
	public UserEntityresponse updateUserEntity(UserEntityrequest userentityrequest, Long id) {
		Optional<UserEntity> checkExistinguser = findById(id);
		if (!checkExistinguser.isPresent())
			log.error("user is not present");
		UserEntity userEntity = UserEntityMapper.MAPPER.fromRequestToEntity(userentityrequest);
		userentityRepository.save(userEntity);
		return UserEntityMapper.MAPPER.fromEntityToResponse(userEntity);
	}

	@Override
	public UserEntityresponse saveUserEntity1(UserEntityrequest userentityrequest, UserType usertype, Long id,
			String username, String password) {
		if (usertype != UserType.ADMIN) {
			log.error("usertype is not admin");
		}
		Optional<UserEntity> userOptional = userentityRepository.findById(id);
		UserEntity user = userOptional.get();
		if (user.getuId() != id) {
			log.error("ADMIN with ID " + id + " not present");
		}
		if (user.getUserType() != UserType.ADMIN) {
			log.error("User with the ID " + id + " is not an admin user.");
		}
		if (!user.getUsername().equals(username)) {
			log.error("user with username" + username + "not found");
		}
		if (!user.getPassword().equals(password)) {
			log.error("Password of ADMIN is incorrect");
		}
		UserEntity userEntity = UserEntityMapper.MAPPER.fromRequestToEntity(userentityrequest);
		userentityRepository.save(userEntity);
		log.info("user entity saved");
		return UserEntityMapper.MAPPER.fromEntityToResponse(userEntity);
	}

	@Override
	public CourseEntityResponse saveCourseEntity1(CourseEntityrequest courserequest, UserType usertype, Long id,
			String username, String password) {
		if (usertype != UserType.AUTHOR) {
			log.error("Only ADMIN users are allowed to perform this operation.");
		}
		Optional<UserEntity> userOptional = userentityRepository.findById(id);
		UserEntity user = userOptional.get();
		if (!user.getuId().equals(id)) {
			log.error("AUTHOR with ID " + id + "is  not found.");
		}
		if (user.getUserType() != UserType.AUTHOR) {
			log.error("User with user id " + id + " is not an author user.");
		}
		if (!user.getUsername().equals(username)) {
			log.error("username with " + username + "of AUTHOR is not present");
		}
		if (!user.getPassword().equals(password)) {
			log.error("Password of AUTHOR is incorrect");
		}
		CourseEntity userEntity = CourseEntityMapper.MAPPER.fromRequestToEntity(courserequest);
		courseentityrepository.save(userEntity);
		log.info("course saved");
		return CourseEntityMapper.MAPPER.fromEntityToResponse(userEntity);
	}

	@Override
	public CourseEntityResponse updateCourseEntity1(CourseEntityrequest courserequest, UserType usertype, Long id,
			String username, String password, Long courseid) {
		if (usertype != UserType.AUTHOR) {
			log.error("Only ADMIN users are allowed to perform this operation.");
		}
		Optional<UserEntity> userOptional = userentityRepository.findById(id);
		UserEntity user = userOptional.get();
		if (user.getuId().equals(id)) {
			log.error("AUTHOR with ID " + id + "isn't found.");
		}
		if (user.getUserType().equals(UserType.AUTHOR)) {
			log.error("User with ID of " + id + " is not an author user.");
		}
		if (!user.getUsername().equals(username)) {
			log.error("username of AUTHOR is incorrect");
		}
		if (!user.getPassword().equals(password)) {
			log.error("Password of AUTHOR is incorrect");
		}
		Optional<CourseEntity> checkExistinguser = courseentityrepository.findById(courseid);
		if (!checkExistinguser.isPresent())
			log.error("Course Id " + courseid + " Not Found!");

		CourseEntity userEntity = CourseEntityMapper.MAPPER.fromRequestToEntity(courserequest);
		courseentityrepository.save(userEntity);
		log.info("course updated");
		return CourseEntityMapper.MAPPER.fromEntityToResponse(userEntity);
	}

	@Override
	public String saveFavoriteEntity(UserType usertype, Long id, String username, String password, Long courseid) {
		if (usertype != UserType.LEARNER) {
			log.error("Only LEARNER are allowed to perform this operation.");
		}
		Optional<UserEntity> userOptional = userentityRepository.findById(id);
		UserEntity user = userOptional.get();
		Optional<CourseEntity> courseOptiona1 = courseentityrepository.findById(courseid);
		CourseEntity course = courseOptiona1.get();
		if (user.getuId().equals(id)) {
			log.error("learner with ID " + id + " not found.");
		}
		if (user.getUserType().equals(UserType.LEARNER)) {
			log.error("User with ID " + id + " is not an learner .");
		}
		if (!user.getPassword().equals(password)) {
			log.error("Password of LEARNER is incorrect");
		}
		FavoriteEntity fav = new FavoriteEntity();
		fav.setCourseId(course);
		fav.setUId(user);
		favoriteentity.save(fav);
		return "favorite added";
	}

	@Override
	public String courseenrollment(UserType usertype, Long id, String username, String password, Long courseid) {
		if (usertype != UserType.LEARNER) {
			log.error("Only LEARNER are allowed to perform this operation.");
		}
		Optional<UserEntity> userOptional = userentityRepository.findById(id);
		UserEntity user = userOptional.get();

		if (user.getuId().equals(id)) {
			log.error("learner with ID " + id + " not found.");
		}
		if (user.getUserType() != UserType.LEARNER) {
			log.error("User with ID " + id + " is not an learner .");
		}
		if (!user.getUsername().equals(username)) {
			log.error("username of LEARNER is incorrect");
		}
		if (!user.getPassword().equals(password)) {
			log.error("Password of LEARNER is incorrect");
		}
		Optional<CourseEntity> userOptiona1 = courseentityrepository.findById(courseid);
		CourseEntity course = userOptiona1.get();
		UserCourseEnrollmentEntity enrol = new UserCourseEnrollmentEntity();
		enrol.setCourseId(course);
		enrol.setUId(user);
		enrollentity.save(enrol);
		return "User enrolled to the course";
	}

}
