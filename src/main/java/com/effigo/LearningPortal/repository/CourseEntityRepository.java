package com.effigo.LearningPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.effigo.LearningPortal.entity.CourseEntity;
@Repository
public interface CourseEntityRepository extends JpaRepository<CourseEntity,Long> {

}
