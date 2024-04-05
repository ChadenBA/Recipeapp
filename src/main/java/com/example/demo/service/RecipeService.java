package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Recipe;
import com.example.demo.repositroy.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private  RecipeRepository recipeRepository;
    
  
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
    
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
    
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }
    
    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
    
    public Recipe updateRecipe(Long id, Recipe recipe) {
        if (recipeRepository.existsById(id)) {
            recipe.setId(id);
            return recipeRepository.save(recipe);
        }
        return null;
    }
    
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}