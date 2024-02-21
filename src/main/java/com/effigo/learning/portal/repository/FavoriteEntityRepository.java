package com.effigo.learning.portal.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.effigo.learning.portal.entity.FavoriteEntity;
@Repository
public interface FavoriteEntityRepository extends JpaRepository<FavoriteEntity,Long> {

}
