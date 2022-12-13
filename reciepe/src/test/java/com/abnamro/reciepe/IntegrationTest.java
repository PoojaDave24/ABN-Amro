package com.abnamro.reciepe;

import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.abnamro.reciepe.Controller.RecipeController;
import com.abnamro.reciepe.Model.ReciepeModel;
import com.abnamro.reciepe.Model.RecipeIngredientsModel;
import com.abnamro.reciepe.Service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class IntegrationTest {
	

		
			@MockBean
		    private RecipeService service;

		    @Autowired
		    private MockMvc mockMvc;
		    
		  

		    @Test
			public void addCourse() throws Exception {

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
				String uri ="/fetchAll";
				mockMvc.perform(MockMvcRequestBuilders.post(uri)
						  .content(asJsonString(reciepeModel))
						  .contentType(MediaType.APPLICATION_JSON)
						  .accept(MediaType.APPLICATION_JSON));
	}
		    public static String asJsonString(final Object obj) {
		        try {
		            final ObjectMapper mapper = new ObjectMapper();
		            final String jsonContent = mapper.writeValueAsString(obj);
		            return jsonContent;
		        } catch (Exception e) {
		            throw new RuntimeException(e);
		        }
		    }  
}



