package com.abnamro.reciepe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.abnamro.reciepe.Controller.RecipeController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;




@EnableSwagger2

@SpringBootApplication(scanBasePackages = {"com.abnamro.reciepe", "com.abnamro.reciepe.Controller"})
public class ReciepeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReciepeApplication.class, args);
	}

}
