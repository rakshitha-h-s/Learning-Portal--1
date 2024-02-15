package com.effigo.LearningPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.effigo.LearningPortal.entity.UserCourseEnrollmentEntity;

@Repository
public interface UserCourseEnrollmentRepository extends JpaRepository<UserCourseEnrollmentEntity,Long> {

}
