package com.example.demo.repositroy;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Recipe;
@Repository 

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
//    @Query("SELECT r FROM recipe r WHERE LOWER(r.ingredients) LIKE %:ingredient%")
List<Recipe> findByIngredientsContaining(String ingredient);

// @Query("SELECT r FROM recipe r WHERE LOWER(r.title) LIKE %:title%")
List<Recipe> findByTitleContaining(String title);

   

    // @Override
    // Recipe save(Recipe recipe);

     @Override
     Optional<Recipe> findById(Long aLong);

}
