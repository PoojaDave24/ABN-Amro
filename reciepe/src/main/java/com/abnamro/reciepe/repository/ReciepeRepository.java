package com.abnamro.reciepe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.abnamro.reciepe.Entity.RecipeEntity;
import com.abnamro.reciepe.Model.ReciepeModel;

@Repository
//public interface ReciepeRepository extends JpaRepository<MainReciepeEntity, Long>{
public interface ReciepeRepository extends JpaRepository<RecipeEntity, Long>,JpaSpecificationExecutor<RecipeEntity>{
	


}
