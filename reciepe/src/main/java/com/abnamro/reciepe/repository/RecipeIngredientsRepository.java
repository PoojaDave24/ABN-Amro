package com.abnamro.reciepe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abnamro.reciepe.Entity.RecipeIngredientsEntity;

@Repository
public interface RecipeIngredientsRepository extends JpaRepository<RecipeIngredientsEntity, Long>{
	void deleteAllByRecipe_Id(Long id);

}
