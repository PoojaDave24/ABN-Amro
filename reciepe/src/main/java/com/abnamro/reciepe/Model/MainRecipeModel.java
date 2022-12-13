package com.abnamro.reciepe.Model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

public class MainRecipeModel {
	
	
	
	@NotBlank
    private String title;

	@NotNull
    private Integer noOfServings;

    @NotBlank
    private String instruction;

    private Boolean isVegetarian;
    
    @Size(min = 1, message = "Ingredients must has at least one element")
    private List<String> ingredients;
	

}
