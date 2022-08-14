package com.qa.recipes.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.qa.recipes.domain.recipes;
import com.qa.recipes.repo.reciperepo;

@Service
public class recipeservice implements ServiceMethods<recipes>{

    //NOT making a new object, creating a variable of the type drinkRepo
    private reciperepo repo;

    //Above is different to this, as we are trying to instantiate an object below (We cant do this to an interface)
//	drinkRepo repos = new drinkRepo();


    //Constructor
    public recipeservice(reciperepo repo) {
        this.repo = repo;
    }

    @Override
    public recipes create(recipes recipes) {
        return this.repo.save(recipes);
    }

    @Override
    public List<recipes> getAll() {
        return this.repo.findAll();
    }

    @Override
    public recipes getById(long id) {
        Optional<recipes>   optionalRecipes = this.repo.findById(id);
        if(optionalRecipes.isPresent()) {
            return optionalRecipes.get();
        }
        return null;
    }

    @Override
    public recipes update(long id, recipes recipes) {
        Optional<recipes> existingDrink = this.repo.findById(id);
        if(existingDrink.isPresent()) {
            recipes existing = existingDrink.get();
            existing.setName(recipes.getName());
            existing.setAuthor(recipes.getAuthor());
            existing.setIngredients(recipes.getIngredients());
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
