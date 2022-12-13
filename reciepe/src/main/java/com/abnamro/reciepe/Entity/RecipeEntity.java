package com.abnamro.reciepe.Entity;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@EqualsAndHashCode(callSuper = true, exclude = {"ingredients"})
@Table(name = "RecipeEntity")

public class RecipeEntity extends BaseEntity{

	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private Integer noOfServings;
	
	@Column
    private String instruction;
	
	@Column
	private Boolean isVegetarian;
	
	
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<RecipeIngredientsEntity> ingredients;
	
	
	public Boolean includeIngredients(String nameOfIngredient) {
        AtomicReference<Boolean> hasIngredient = new AtomicReference<>(false);
        this.getIngredients().forEach(ingredientEntity -> {
            if (ingredientEntity.getName().equalsIgnoreCase(nameOfIngredient)) {
                hasIngredient.set(true);
            }
        });

        return hasIngredient.get();
    }

    public Boolean excludeIngredients(String nameOfIngredient) {
        AtomicReference<Boolean> hasIngredient = new AtomicReference<>(true);
        this.getIngredients().forEach(ingredientEntity -> {
            if (ingredientEntity.getName().equalsIgnoreCase(nameOfIngredient)) {
                hasIngredient.set(false);
            }
        });

        return hasIngredient.get();
    }
	
}
