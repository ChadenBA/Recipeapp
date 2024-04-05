package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Recipe;
import com.example.demo.service.RecipeService;

@RestController
@RequestMapping("/api/recipes")
public class RecipeContrl {


  @Autowired
   private RecipeService recipeService;
//      public RecipeContrl(RecipeService recipeService){
//       this.recipeService=recipeService;
//    }

   // This method maps to GET requests to /api/recipes
    // @GetMapping
    // public List<Recipe>getAllRecipes() {
    //     List<Recipe> recipes = recipeService.getAllRecipes();
    //     if (recipes != null) {
    //         return new ResponseEntity<>(recipes, HttpStatus.OK);
    //     } else {
    //         System.err.println("hebhb");
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }
    @GetMapping
    public List<Recipe> getRecepies(){
        return recipeService.getAllRecipes();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        if (recipe != null) {
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

     @PostMapping
     public void addRecipe(@RequestBody Recipe recipe) {
       recipeService.addRecipe(recipe);
       
     }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {
        Recipe updatedRecipe = recipeService.updateRecipe(id, recipe);
        if (updatedRecipe != null) {
            return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}