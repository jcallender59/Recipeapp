package com.qa.recipes.repo;
//import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qa.recipes.domain.Recipes;

public interface recipeRepo extends JpaRepository<Recipes, Long>{

      //  @Query(value = "SELECT * FROM recipes", nativeQuery = true)
       // public List<Recipes> allRecipes();


}