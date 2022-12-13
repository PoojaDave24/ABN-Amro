package com.abnamro.reciepe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.abnamro.reciepe.Entity.RecipeEntity;
import com.abnamro.reciepe.Model.IngredientSearchModel;
import com.abnamro.reciepe.Model.ReciepeModel;
import com.abnamro.reciepe.Model.RecipeIngredientsModel;
import com.abnamro.reciepe.Model.RecipeSearchModel;
import com.abnamro.reciepe.Service.RecipeService;
import com.abnamro.reciepe.exception.BusinessException;
import com.abnamro.reciepe.repository.ReciepeRepository;

public class RecipeServiceTest {
	
	@InjectMocks
	private RecipeService recipeService;
	
	@Mock
	private ReciepeRepository reciepeRepository;
	
	@Mock
	ModelMapper mapper ;
	
	@BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }
	private ReciepeModel getDemoData()
	{
		ReciepeModel reciepeModel = new ReciepeModel();
		List<RecipeIngredientsModel> recipeIngredientsModel = new ArrayList<>();
		 RecipeIngredientsModel potato = RecipeIngredientsModel.builder().name("tomato").build();
		 RecipeIngredientsModel tomato = RecipeIngredientsModel.builder().name("potato").build();
		 recipeIngredientsModel.add(potato);
		 recipeIngredientsModel.add(tomato);
		 reciepeModel.setInstruction("cook in microwave");
		reciepeModel.setIsVegetarian(true);
		reciepeModel.setNoOfServings(2);
		reciepeModel.setIngredients(recipeIngredientsModel);
		return reciepeModel;
	}
	private List<RecipeEntity> getListDemoData()
	{
		List<ReciepeModel> listRecMod =  new ArrayList<>();
		ReciepeModel rm = new ReciepeModel();
		listRecMod.add(getDemoData());
		ReciepeModel reciepeModel = new ReciepeModel();
		List<RecipeIngredientsModel> recipeIngredientsModel = new ArrayList<>();
		 RecipeIngredientsModel chilly = RecipeIngredientsModel.builder().name("chilly").build();
		 RecipeIngredientsModel salt = RecipeIngredientsModel.builder().name("salt").build();
		 recipeIngredientsModel.add(chilly);
		 recipeIngredientsModel.add(salt);
		 reciepeModel.setInstruction("use oven");
		reciepeModel.setIsVegetarian(false);
		reciepeModel.setNoOfServings(5);
		reciepeModel.setIngredients(recipeIngredientsModel);
		listRecMod.add(reciepeModel);
		ModelMapper mapper = new ModelMapper();
		List<RecipeEntity> recipeentitylist = new ArrayList<RecipeEntity>();
		for(ReciepeModel recmod : listRecMod)
		{
			RecipeEntity recent = mapper.map(recmod, RecipeEntity.class );
			recipeentitylist.add(recent);
		}
		return recipeentitylist;
	}
	private RecipeSearchModel getSearchModelData() {
		
		RecipeSearchModel recSearchMdl=  new RecipeSearchModel();
		IngredientSearchModel ingsrchmdl =  new IngredientSearchModel();
		ingsrchmdl.setIncludeSpecificIngredient(true);
		ingsrchmdl.setSpecificIngredient("potato");
		recSearchMdl.setIsVegeterian(true);
		recSearchMdl.setNumberOfServings(3);
		recSearchMdl.setIngredient(ingsrchmdl);
		recSearchMdl.setTextSearchInstruction("use oven");
		return recSearchMdl;
	}
	@Test
	void add() {
		ReciepeModel obj =getDemoData();
		when(reciepeRepository.save(any(RecipeEntity.class))).thenReturn(null);
		ReciepeModel reciepeModel1 = recipeService.add(obj);
		assertEquals(obj.getNoOfServings(),reciepeModel1.getNoOfServings());
		}
	
	@Test
	void getAll()
	{
		List<RecipeEntity> recipes = getListDemoData();
		when(reciepeRepository.findAll()).thenReturn(recipes);
		List<ReciepeModel> recipeFromService = recipeService.getAll();
		assertEquals(recipes.get(0).getIsVegetarian(),recipeFromService.get(0).getIsVegetarian());
	}
	
	
	
	
	
	
	

}
