package com.qa.recipes.controller;

import java.util.List;

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

import com.qa.recipes.domain.Recipes;
import com.qa.recipes.service.recipeService;

@RestController
@RequestMapping("/recipes")
public class    RecipesController {

    private recipeService service;

    private RecipesController(recipeService service) {
        this.service = service;
    }

    //Create
    @PostMapping("/create")
    public ResponseEntity<Recipes> createRecipes(@RequestBody Recipes Recipes) {
        return new ResponseEntity<Recipes>(this.service.create(Recipes), HttpStatus.CREATED);
    }

    //Read All
    @GetMapping("/readAll")
    public ResponseEntity<List<Recipes>> readAllRecipes(){
        return new ResponseEntity<List<Recipes>>(this.service.getAll(), HttpStatus.OK);
    }

    //Read By Id
    @GetMapping("/readById/{id}")
    public ResponseEntity<Recipes> readRecipesById(@PathVariable long id){
        return new ResponseEntity<Recipes>(this.service.getById(id), HttpStatus.OK);
    }

    //Update
    @PutMapping("/update/{id}")
    public ResponseEntity<Recipes> updateRecipes(@PathVariable long id, @RequestBody Recipes Recipes){
        return new ResponseEntity<Recipes>(this.service.update(id, Recipes), HttpStatus.ACCEPTED);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteRecipes(@PathVariable long id){
        //Line below uses a Ternary Operator, basically says IF service.delete method goes through, RETURN no content ELSE return not found
        return (this.service.delete(id)) ? new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
    }

}


