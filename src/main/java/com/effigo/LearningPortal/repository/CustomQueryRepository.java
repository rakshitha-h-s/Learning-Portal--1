package com.effigo.LearningPortal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.effigo.LearningPortal.entity.UserEntity;
@Repository
public interface CustomQueryRepository extends JpaRepository<UserEntity, Long> {
	//accessing the username with course name of favorite course by joining 3 tables
	@Query(value = "SELECT u.username AS username, c.course_name AS course_name " +
            "FROM user_entity u " +
            "JOIN favorite_entity f ON u.u_id = f.u_id " +
            "JOIN course_entity c ON f.course_id = c.course_id",
    nativeQuery = true)
     List<Object[]> getUsersWithFavoriteCoursesNative();
     //accessing the user along the course to which they have enrolled
     @Query(value="SELECT u.username AS username,c.course_name AS course_name "+
     "FROM user_entity u "+
    		 "JOIN user_course_enrollment_entity e ON u.u_id=e.u_id "+
     "JOIN course_entity c ON e.course_id=c.course_id",nativeQuery=true)
     List<Object[]> getUserWithEnrolledCourseNative();
     //accessing the course along with the category name 
     @Query(value="SELECT c.course_name AS coursename,d.category_type AS category_name "+
     "FROM course_entity c "+
     "JOIN category_entity d ON c.category_id=d.category_id",nativeQuery=true)
     List<Object[]> getCourseWithCategory();

}
