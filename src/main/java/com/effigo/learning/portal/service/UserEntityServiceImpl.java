package com.effigo.learning.portal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserEntityServiceImpl {
	@Autowired
	UserEntityMapper userEntityMapper;
	@Autowired
	CourseEntityMapper courseEntityMapper;
	@Autowired
	UserEntityRepository userentityRepository;
	@Autowired
	CourseEntityRepository courseentityrepository;
	@Autowired
	FavoriteEntityRepository favoriteentity;
	@Autowired
	UserCourseEnrollmentRepository enrollentity;

	public List<UserEntitydto> findAllUser() {
		List<UserEntity> user = userentityRepository.findAll();
		return userEntityMapper.toDto(user);
	}

	public UserEntitydto findById(Long id) {
		Optional<UserEntity> user = userentityRepository.findById(id);
		if (user.isPresent()) {
			UserEntity entity = user.get();
			return userEntityMapper.toDto(entity);
		}
		throw new EntityNotFoundException("User with ID " + id + " not found");
	}

	public void deleteUserentity(Long id) {
		userentityRepository.deleteById(id);
	}

	public UserEntitydto saveUserEntity(UserEntitydto userentityrequest) {
		userentityrequest.setCreatedOn(LocalDateTime.now());
		userentityrequest.setUpdatedOn(LocalDateTime.now());
		UserEntity userEntity = userEntityMapper.toEntity(userentityrequest);
		userEntity = userentityRepository.save(userEntity);
		return userEntityMapper.toDto(userEntity);
	}

	public UserEntitydto updateUserEntity(UserEntitydto userentityrequest, Long id) {
		Optional<UserEntitydto> checkExistinguser = Optional.ofNullable(findById(id));
		if (!checkExistinguser.isPresent()) {
			log.error("user is not present");
			throw new EntityNotFoundException("User with ID " + id + " not found");
		}
		UserEntity userEntity = userEntityMapper.toEntity(userentityrequest);
		userentityRepository.save(userEntity);
		return userEntityMapper.toDto(userEntity);
	}

	public UserEntitydto saveUserEntity1(UserEntitydto userentityrequest, UserType usertype, Long id, String username,
			String password) {
		Boolean flag = false;
		if (usertype != UserType.ADMIN) {
			log.error("usertype is not admin");
			flag = true;
		}
		Optional<UserEntity> userOptional = userentityRepository.findById(id);
		UserEntity user = userOptional.get();
		if (!user.getuId().equals(id)) {
			flag = true;
			log.error("ADMIN with ID " + id + " not present");
		}
		if (user.getUserType() != UserType.ADMIN) {
			flag = true;
			log.error("User with the ID " + id + " is not an admin user.");
		}
		if (!user.getUsername().equals(username)) {
			flag = true;
			log.error("user with username" + username + "not found");
		}
		if (!user.getPassword().equals(password)) {
			flag = true;
			log.error("Password of ADMIN is incorrect");
		}
		if (Boolean.FALSE.equals(flag)) {
			UserEntity userEntity = userEntityMapper.toEntity(userentityrequest);
			userentityRepository.save(userEntity);
			log.info("user entity saved");
			return userEntityMapper.toDto(userEntity);
		}
		return null;
	}

	public CourseEntitydto saveCourseEntity1(CourseEntitydto courserequest, UserType usertype, Long id, String username,
			String password) {
		Boolean flag = true;
		if (usertype != UserType.AUTHOR) {
			log.error("Only ADMIN users are allowed to perform this operation.");
			flag = false;
		}
		Optional<UserEntity> userOptional = userentityRepository.findById(id);
		UserEntity user = userOptional.get();
		if (!user.getuId().equals(id)) {
			flag = false;
			log.error("AUTHOR with ID " + id + "is  not found.");
		}
		if (user.getUserType() != UserType.AUTHOR) {
			flag = false;
			log.error("User with user id " + id + " is not an author user.");
		}
		if (!user.getUsername().equals(username)) {
			flag = false;
			log.error("username with " + username + "of AUTHOR is not present");
		}
		if (!user.getPassword().equals(password)) {
			flag = false;
			log.error("Password of AUTHOR is incorrect");
		}
		if (Boolean.TRUE.equals(flag)) {
			CourseEntity courseEntity = courseEntityMapper.toEntity(courserequest);
			courseentityrepository.save(courseEntity);
			log.info("course saved");
			return courseEntityMapper.toDto(courseEntity);
		}
		return null;
	}

	public CourseEntitydto updateCourseEntity1(CourseEntitydto courserequest, UserType usertype, Long id,
			String username, String password, String courseid) {
		Boolean flag = false;
		if (usertype != UserType.AUTHOR) {
			log.error("Only ADMIN users are allowed to perform this operation.");
			flag = true;
		}
		Optional<UserEntity> userOptional = userentityRepository.findById(id);
		UserEntity user = userOptional.get();
		if (!user.getuId().equals(id)) {
			log.error("AUTHOR with ID " + id + "isn't found.");
			flag = true;
		}
		if (user.getUserType().equals(UserType.AUTHOR)) {
			log.error("User with ID of " + id + " is not an author user.");
			flag = true;
		}
		if (!user.getUsername().equals(username)) {
			log.error("username of AUTHOR is incorrect");
			flag = true;
		}
		if (!user.getPassword().equals(password)) {
			log.error("Password of AUTHOR is incorrect");
			flag = true;
		}
		Optional<CourseEntity> checkExistinguser = courseentityrepository.findById(courseid);
		if (!checkExistinguser.isPresent())
			log.error("Course Id " + courseid + " Not Found!");

		if (Boolean.FALSE.equals(flag)) {
			CourseEntity courseEntity = courseEntityMapper.toEntity(courserequest);
			courseentityrepository.save(courseEntity);
			log.info("course updated");
			return courseEntityMapper.toDto(courseEntity);
		} else {
			return null;
		}
	}

	public String saveFavoriteEntity(UserType usertype, Long id, String username, String password, String courseid) {
		Boolean flag = false;
		if (usertype != UserType.LEARNER) {
			log.error("Only LEARNER are allowed to perform this operation.");
			flag = true;
		}
		Optional<UserEntity> userOptional = userentityRepository.findById(id);
		UserEntity user = userOptional.get();
		Optional<CourseEntity> courseOptiona1 = courseentityrepository.findById(courseid);
		CourseEntity course = courseOptiona1.get();
		if (!user.getuId().equals(id)) {
			log.error("learner with ID " + id + " not found.");
			flag = true;
		}
		if (!user.getUserType().equals(UserType.LEARNER)) {
			log.error("User with ID " + id + " is not an learner .");
			flag = true;
		}
		if (!user.getUsername().equals(username)) {
			log.error("username of LEARNER is incorrect");
			flag = true;
		}
		if (!user.getPassword().equals(password)) {
			log.error("Password of LEARNER is incorrect");
			flag = true;
		}
		if (Boolean.FALSE.equals(flag)) {
			FavoriteEntity fav = new FavoriteEntity();
			fav.setCourseId(course);
			fav.setuId(user);
			favoriteentity.save(fav);
			return "favorite added";
		} else {
			return "there was an error";
		}
	}

	public String courseenrollment(UserType usertype, Long id, String username, String password, String courseid) {
		Boolean flag = false;
		if (usertype != UserType.LEARNER) {
			log.error("Only LEARNER are allowed to perform this operation.");
			flag = true;
		}
		Optional<UserEntity> userOptional = userentityRepository.findById(id);
		UserEntity user = userOptional.get();

		if (!user.getuId().equals(id)) {
			log.error("learner with ID " + id + " not found.");
			flag = true;
		}
		if (!user.getUserType().equals(UserType.LEARNER)) {
			log.error("User with ID " + id + " is not an learner .");
			flag = true;
		}
		if (!user.getUsername().equals(username)) {
			log.error("username of LEARNER is incorrect");
			flag = true;
		}
		if (!user.getPassword().equals(password)) {
			log.error("Password of LEARNER is incorrect");
			flag = true;
		}
		if (Boolean.FALSE.equals(flag)) {
			Optional<CourseEntity> userOptiona1 = courseentityrepository.findById(courseid);
			CourseEntity course = userOptiona1.get();
			UserCourseEnrollmentEntity enrol = new UserCourseEnrollmentEntity();
			enrol.setCourseId(course);
			enrol.setUId(user);
			enrollentity.save(enrol);
			return "User enrolled to the course";
		}
		return "There was an error";
	}

}
