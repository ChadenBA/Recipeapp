package com.example.demo.config;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Recipe;
import com.example.demo.repositroy.RecipeRepository;

@Configuration
public class RecipeConfig {
    @Bean
    CommandLineRunner commandLineRunner (RecipeRepository repository){
        return args -> {
            Recipe a = new Recipe("Chocolate Cake", " 1 cup boiling water", "1. Preheat oven to 350°F. Grease and flour two 9-inch round cake pans. 2. Sift together flour");
            Recipe b = new Recipe("Spaghetti Carbonara", " 2 cloves minced garlic, 1/4 tsp black pepper, Salt to taste, Chopped parsley for garnish", "1. Cook spaghetti until al dente. Reserve 1 cup pasta water. 2. Cook bacon until crispy. Remove ");
            Recipe c = new Recipe("Caesar Salad", "2 anchovy fillets (optional), black pepper to taste, Croutons for garnish", "1. Wash and dry lettuce. Tear into");
            
            repository.saveAll(List.of(a, b, c));

        };
       
    }
}
