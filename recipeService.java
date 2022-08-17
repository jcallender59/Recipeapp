package com.qa.recipes.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.qa.recipes.domain.Recipes;
import com.qa.recipes.repo.recipeRepo;

@Service
public class recipeService implements ServiceMethods<Recipes>{

    //NOT making a new object, creating a variable of the type RecipesRepo
    private recipeRepo repo;

    //Above is different to this, as we are trying to instantiate an object below (We cant do this to an interface)
//	RecipesRepo repos = new RecipesRepo();


    //Constructor
    public recipeService(recipeRepo repo) {
        this.repo = repo;
    }

    @Override
    public Recipes create(Recipes recipes) {
        return this.repo.save(recipes);
    }

    @Override
    public List<Recipes> getAll() {
        return this.repo.findAll();
    }

    @Override
    public Recipes getById(long id) {
        Optional<Recipes> optionalRecipes = this.repo.findById(id);
        if(optionalRecipes.isPresent()) {
            return optionalRecipes.get();
        }
        return null;
    }

    @Override
    public Recipes update(long id, Recipes Recipes) {
        Optional<Recipes> existingRecipes = this.repo.findById(id);
        if(existingRecipes.isPresent()) {
            Recipes existing = existingRecipes.get();
            existing.setName(Recipes.getName());
            existing.setAuthor(Recipes.getAuthor());
            existing.setIngredients(Recipes.getIngredients());
            return this.repo.saveAndFlush(existing);
        }

        return null;
    }

    //Deletes the ID, and checks the ID has actually been deleted (Returns true)
    @Override
    public boolean delete(long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

}






