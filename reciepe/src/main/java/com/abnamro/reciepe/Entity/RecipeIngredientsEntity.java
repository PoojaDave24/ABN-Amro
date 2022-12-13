package com.abnamro.reciepe.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)

public class RecipeIngredientsEntity extends BaseEntity{
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne
    @JoinColumn(name = "RECIPE_ID",
            foreignKey = @ForeignKey(name = "FK_RECIPE"), nullable = false)
    private RecipeEntity recipe;

}
