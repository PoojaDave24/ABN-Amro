package com.abnamro.reciepe.Model;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReciepeModel extends BaseModel{
	
	
	 
	@NotBlank
    private String title;

	@NotNull
    private Integer noOfServings;

    @NotBlank
    private String instruction;

    private Boolean isVegetarian;

    @Size(min = 1, message = "Ingredients must has at least one element")
    private List<RecipeIngredientsModel> ingredients;

	}
