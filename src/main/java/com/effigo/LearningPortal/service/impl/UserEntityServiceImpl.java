package com.effigo.LearningPortal.service.impl;
import com.effigo.LearningPortal.dto.mapper.CourseEntityMapper;
import com.effigo.LearningPortal.dto.mapper.UserEntityMapper;
import com.effigo.LearningPortal.dto.request.CourseEntityrequest;
import com.effigo.LearningPortal.dto.request.UserEntityrequest;
import com.effigo.LearningPortal.dto.response.CourseEntityResponse;
import com.effigo.LearningPortal.dto.response.UserEntityresponse;
import com.effigo.LearningPortal.entity.CourseEntity;
import com.effigo.LearningPortal.entity.FavoriteEntity;
import com.effigo.LearningPortal.entity.UserEntity;
import com.effigo.LearningPortal.entity.UserEntity.UserType;
import com.effigo.LearningPortal.repository.CourseEntityRepository;
import com.effigo.LearningPortal.repository.FavoriteEntityRepository;
import com.effigo.LearningPortal.repository.UserEntityRepository;
import com.effigo.LearningPortal.service.CourseService;
import com.effigo.LearningPortal.service.UserEntityService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Transactional
public class UserEntityServiceImpl implements UserEntityService{
	
	private final UserEntityRepository userentityRepository;
	private final CourseEntityRepository courseentityrepository;
	private final FavoriteEntityRepository favoriteentity;
	public UserEntityServiceImpl(UserEntityRepository userentityRepository, CourseEntityRepository courseentityrepository,CourseService courseservice,FavoriteEntityRepository favoriteentity)
	{
		this.userentityRepository=userentityRepository;
		this.courseentityrepository = courseentityrepository;
		this.favoriteentity = favoriteentity;
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

	@Override
	public UserEntityresponse updateUserEntity(UserEntityrequest userentityrequest, Long id) {
		Optional<UserEntity> checkExistinguser = findById(id);
        if (! checkExistinguser.isPresent())
            throw new RuntimeException("User Id "+ id + " Not Found!");

        UserEntity userEntity = UserEntityMapper.MAPPER.fromRequestToEntity(userentityrequest);
        userentityRepository.save(userEntity);
        return UserEntityMapper.MAPPER.fromEntityToResponse(userEntity);
	}

    @Override
    public UserEntityresponse saveUserEntity1(UserEntityrequest userentityrequest,UserType usertype,Long id,String password)
    {
    	   if (usertype != UserType.ADMIN) {
               throw new IllegalArgumentException("Only ADMIN users are allowed to perform this operation.");
           }
           Optional<UserEntity> userOptional = userentityRepository.findById(id);
           UserEntity user = userOptional.get();
           if (user.getU_id()!=id) {
               throw new IllegalArgumentException("ADMIN with ID " + id + " not found.");
           }  
           if (user.getUserType() != UserType.ADMIN) {
               throw new IllegalArgumentException("User with ID " + id + " is not an admin user.");
           }
           if(!user.getPassword().equals(password)) {
        	   throw new IllegalArgumentException("Password of ADMIN is incorrect");
           }
         UserEntity userEntity = UserEntityMapper.MAPPER.fromRequestToEntity(userentityrequest);
         userentityRepository.save(userEntity);
         return UserEntityMapper.MAPPER.fromEntityToResponse(userEntity);
    }
    @Override
    public CourseEntityResponse saveCourseEntity1(CourseEntityrequest courserequest,UserType usertype,Long id,String password) {
    	if (usertype != UserType.AUTHOR) {
            throw new IllegalArgumentException("Only ADMIN users are allowed to perform this operation.");
        }
        Optional<UserEntity> userOptional = userentityRepository.findById(id);
        UserEntity user = userOptional.get();
        if (user.getU_id()!=id) {
            throw new IllegalArgumentException("AUTHOR with ID " + id + " not found.");
        }   
        if (user.getUserType() != UserType.AUTHOR) {
            throw new IllegalArgumentException("User with ID " + id + " is not an author user.");
        }
        if(!user.getPassword().equals(password)) {
     	   throw new IllegalArgumentException("Password of AUTHOR is incorrect");
        }
        CourseEntity userEntity = CourseEntityMapper.MAPPER.fromRequestToEntity(courserequest);
        courseentityrepository.save(userEntity);
        return CourseEntityMapper.MAPPER.fromEntityToResponse(userEntity);	
    }
    @Override
    public CourseEntityResponse updateCourseEntity1(CourseEntityrequest courserequest,UserType usertype,Long id,String password,Long courseid) {
    	if (usertype != UserType.AUTHOR) {
            throw new IllegalArgumentException("Only ADMIN users are allowed to perform this operation.");
        }
        Optional<UserEntity> userOptional = userentityRepository.findById(id);
        UserEntity user = userOptional.get();
        if (user.getU_id()!=id) {
            throw new IllegalArgumentException("AUTHOR with ID " + id + " not found.");
        } 
        if (user.getUserType() != UserType.AUTHOR) {
            throw new IllegalArgumentException("User with ID " + id + " is not an author user.");
        }
        if(!user.getPassword().equals(password)) {
     	   throw new IllegalArgumentException("Password of AUTHOR is incorrect");
        }
        Optional<CourseEntity> checkExistinguser = courseentityrepository.findById(courseid);
        if (! checkExistinguser.isPresent())
            throw new RuntimeException("Course Id "+ courseid + " Not Found!");

        CourseEntity userEntity = CourseEntityMapper.MAPPER.fromRequestToEntity(courserequest);
        courseentityrepository.save(userEntity);
        return CourseEntityMapper.MAPPER.fromEntityToResponse(userEntity);
    }
    @Override
    public String saveFavoriteEntity(UserType usertype,String username,Long courseid) 
    {
    	if (usertype != UserType.LEARNER) {
            throw new IllegalArgumentException("Only LEARNER are allowed to perform this operation.");
        }
        Long id=isValidUser(username);
        Optional<UserEntity> userOptional = userentityRepository.findById(id);
        UserEntity user = userOptional.get();
        
        if (user.getU_id()!=id) {
            throw new IllegalArgumentException("learner with ID " + id + " not found.");
        }   
        if (user.getUserType() != UserType.LEARNER) {
            throw new IllegalArgumentException("User with ID " + id + " is not an learner .");
        }
        Optional<CourseEntity> userOptiona1 = courseentityrepository.findById(courseid);
        CourseEntity course = userOptiona1.get();
       FavoriteEntity fav=new FavoriteEntity();
       fav.setCourse_id(course);
    	fav.setU_id(user);
    	favoriteentity.save(fav);
    	return "favorite added";
	}
    @Autowired
    private UserEntityRepository userRepository;
    public Long isValidUser(String username) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        UserEntity user=userOptional.get();
        return user.getU_id();
    }
    
}


	

