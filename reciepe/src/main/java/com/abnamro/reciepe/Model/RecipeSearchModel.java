package com.abnamro.reciepe.Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeSearchModel {
		
	private Boolean isVegeterian;
	    private Integer numberOfServings;
	    private IngredientSearchModel ingredient;
	    private String textSearchInstruction;
	}


