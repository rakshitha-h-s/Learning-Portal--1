package com.effigo.LearningPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.effigo.LearningPortal.entity.CategoryEntity;
@Repository
public interface CategoryEntityRepository extends JpaRepository<CategoryEntity,Long>{

}
