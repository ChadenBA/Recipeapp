package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.Controller.RecipeContrl;
import com.example.demo.model.Recipe;
import com.example.demo.service.RecipeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DemoApplicationTests {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeContrl recipeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRecepies() {
        List<Recipe> recipes = new ArrayList<>();
        // Add some test recipes to the list
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        recipe1.setTitle("Recipe 1");
        // Add more test recipes if needed

        recipes.add(recipe1);
        // Add more recipes to the list if needed

        when(recipeService.getAllRecipes()).thenReturn(recipes);

        List<Recipe> result = recipeController.getRecepies();

        assertEquals(recipes.size(), result.size());
        // Add more assertions if needed
    }

    @Test
    public void testGetRecipeById() {
        Long id = 1L;
        Recipe recipe = new Recipe();
        recipe.setId(id);
        recipe.setTitle("Test Recipe");

        when(recipeService.getRecipeById(id)).thenReturn(recipe);

        ResponseEntity<Recipe> response = recipeController.getRecipeById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recipe, response.getBody());
    }

   public void testAddRecipe() {
        Recipe recipeToAdd = new Recipe();
        recipeToAdd.setTitle("New Recipe");

        recipeController.addRecipe(recipeToAdd);

        verify(recipeService).addRecipe(recipeToAdd);
    }

    @Test
    public void testUpdateRecipe() {
        Long id = 1L;
        Recipe recipeToUpdate = new Recipe();
        recipeToUpdate.setTitle("Updated Recipe");

        when(recipeService.updateRecipe(id, recipeToUpdate)).thenReturn(recipeToUpdate);

        ResponseEntity<Recipe> response = recipeController.updateRecipe(id, recipeToUpdate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recipeToUpdate, response.getBody());
    }

    @Test
    public void testDeleteRecipe() {
        Long id = 1L;

        ResponseEntity<Void> response = recipeController.deleteRecipe(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(response.getBody() == null || response.getBody().toString().isEmpty());
        verify(recipeService).deleteRecipe(id);
    }
}

