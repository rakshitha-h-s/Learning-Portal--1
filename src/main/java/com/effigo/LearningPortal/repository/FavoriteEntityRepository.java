package com.effigo.LearningPortal.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.effigo.LearningPortal.entity.FavoriteEntity;
@Repository
public interface FavoriteEntityRepository extends JpaRepository<FavoriteEntity,Long> {

}
