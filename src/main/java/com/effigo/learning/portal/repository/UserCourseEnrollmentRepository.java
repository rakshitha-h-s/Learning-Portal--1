package com.effigo.learning.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.effigo.learning.portal.entity.UserCourseEnrollmentEntity;

@Repository
public interface UserCourseEnrollmentRepository extends JpaRepository<UserCourseEnrollmentEntity, Long> {

}
