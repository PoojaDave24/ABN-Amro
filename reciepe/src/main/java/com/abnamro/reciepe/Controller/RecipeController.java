package com.abnamro.reciepe.Controller;

import java.util.List;

import javax.validation.Valid;

//import org.h2.util.json.JSONObject;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abnamro.reciepe.Model.ReciepeModel;
//import com.abnamro.reciepe.Model.RecipeListModel;
import com.abnamro.reciepe.Model.RecipeSearchModel;
import com.abnamro.reciepe.Service.RecipeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RecipeController {


	private final RecipeService recipeService;
	
	@PostMapping("/addRecipe")
	HttpStatus add(@Valid @RequestBody ReciepeModel model) {
		this.recipeService.add(model);
		return  HttpStatus.OK;
	}

	@GetMapping("/fetchAll")
	ResponseEntity<List<ReciepeModel>> getAll(){
		return new ResponseEntity<>(this.recipeService.getAll(),HttpStatus.OK);
	}

	@PutMapping("/updateRecipe") 
	HttpStatus update(@Valid @RequestBody ReciepeModel model) {
		this.recipeService.update(model);
		return  HttpStatus.OK;
	}
	@DeleteMapping("/deleteRecipe/{id}")
	HttpStatus delete (@PathVariable Long id)
	{
		this.recipeService.delete(id) ;
		return HttpStatus.OK;
	}

	@PostMapping("/search")
	ResponseEntity<List<ReciepeModel>> getAllByFilter(@Valid @RequestBody RecipeSearchModel model) {
		return new ResponseEntity<>(this.recipeService.getByFilter(model), HttpStatus.OK);
	}


}
