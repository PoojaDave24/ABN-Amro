package com.abnamro.reciepe.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.abnamro.reciepe.Entity.RecipeEntity;
import com.abnamro.reciepe.Entity.RecipeIngredientsEntity;
import com.abnamro.reciepe.Model.MainRecipeModel;
import com.abnamro.reciepe.Model.ReciepeModel;
import com.abnamro.reciepe.Model.RecipeSearchModel;
import com.abnamro.reciepe.exception.BusinessException;
import com.abnamro.reciepe.repository.ReciepeRepository;
import com.abnamro.reciepe.repository.RecipeIngredientsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class RecipeService {

	private final ReciepeRepository reciepeRepository;

	private final RecipeIngredientsRepository recipeIngredientsRepository;

	@Transactional
	public ReciepeModel add(ReciepeModel model) {
		checkHasIngredients(model);
		if (model.getIsVegetarian()== null) {model.setIsVegetarian(false);} 
		ModelMapper mapper = new ModelMapper();
		RecipeEntity recipe = mapper.map(model,RecipeEntity.class);
		recipe.getIngredients().
		forEach(ingredient-> ingredient.setRecipe(recipe));
		this.reciepeRepository.save(recipe);
		ReciepeModel rm = mapper.map(recipe,ReciepeModel.class);
		return rm;
	}


	@Transactional
	public List<ReciepeModel> getAll(){

		List<RecipeEntity> recipes = this.reciepeRepository.findAll();
		List<ReciepeModel> rmlist = new ArrayList<ReciepeModel>();
		ModelMapper mapper = new ModelMapper();
		for(RecipeEntity re : recipes)
		{
			ReciepeModel rm = mapper.map(re, ReciepeModel.class );
			rmlist.add(rm);
		}
		return rmlist;

	}

	@Transactional
	public String update(ReciepeModel model) { 
		try {
			checkIdAvailableBeforeUpdate(model.getId());
		} catch (Exception e) {
			throw new BusinessException("id is not exist for update", HttpStatus.UNPROCESSABLE_ENTITY.value());
		}
		ModelMapper mapper = new ModelMapper();
		RecipeEntity recipe = mapper.map(model, RecipeEntity.class);
		if(model.getIngredients() != null && !model.getIngredients().isEmpty()) {
			removeExistedIngrediests(model); 
			recipe.getIngredients().forEach(ingredient -> ingredient.setRecipe(recipe)); } 
		this.reciepeRepository.save(recipe); 
		return "Reciepe Updated Successfully ! "; }

	@Transactional
	public String delete(Long id) { 
		try {
			checkIdAvailableBeforeUpdate(id);
		} catch (Exception e) {
			throw new BusinessException("id is not exist for delete", HttpStatus.UNPROCESSABLE_ENTITY.value());
		}
		this.reciepeRepository.deleteById(id);
		return "Reciepe Deleted Successfully ! "; 
	}


	public List<ReciepeModel> getByFilter(RecipeSearchModel recipesearchmodel) {

		List<RecipeEntity> allrecipes = this.reciepeRepository.findAll();
		if (recipesearchmodel.getIsVegeterian()!=null) {
			allrecipes = allrecipes.stream().filter(RecipeEntity-> RecipeEntity.getIsVegetarian().equals(recipesearchmodel.getIsVegeterian())).toList();
		}

		if(recipesearchmodel.getNumberOfServings()!=null)
		{
			allrecipes= allrecipes.stream().filter(RecipeEntity->RecipeEntity.getNoOfServings().equals(recipesearchmodel.getNumberOfServings())).toList();
		}
		if(recipesearchmodel.getTextSearchInstruction()!=null)
		{
			allrecipes=allrecipes.stream().filter(RecipeEntity->RecipeEntity.getInstruction().contains(recipesearchmodel.getTextSearchInstruction())).toList();
		}
		if(recipesearchmodel.getIngredient().getIncludeSpecificIngredient()!=null) {
			allrecipes = allrecipes.stream().filter(RecipeEntity-> RecipeEntity.includeIngredients(recipesearchmodel.getIngredient().getSpecificIngredient())).toList();
		}
		else
		{
			allrecipes = allrecipes.stream().filter(RecipeEntity->RecipeEntity.excludeIngredients(recipesearchmodel.getIngredient().getSpecificIngredient())).toList();
		}
		List<ReciepeModel> rmlist = new ArrayList<ReciepeModel>();
		ModelMapper mapper = new ModelMapper();
		for(RecipeEntity re : allrecipes)
		{
			ReciepeModel rm = mapper.map(re, ReciepeModel.class );
			rmlist.add(rm);
		}
		return rmlist;
	}


	private void checkHasIngredients(ReciepeModel model) {
		if (model.getIngredients() == null || model.getIngredients().isEmpty()) {
			throw new BusinessException("ingredients must at least has on elements",
					HttpStatus.UNPROCESSABLE_ENTITY.value());
		}
	}

	private void checkHasId(ReciepeModel model) {
		if (model.getId() == null) {
			throw new BusinessException("id is not exist for update", HttpStatus.UNPROCESSABLE_ENTITY.value());
		}
	}
	private void removeExistedIngrediests(ReciepeModel model) {
		recipeIngredientsRepository.deleteAllByRecipe_Id(model.getId());
	}
	private void checkIdAvailableBeforeUpdate(Long id) {
		Optional<RecipeEntity> obj = reciepeRepository.findById(id);
		if(obj.isEmpty()) {
			throw  new BusinessException("id is not exist for update", HttpStatus.UNPROCESSABLE_ENTITY.value());
		}


	}
}

