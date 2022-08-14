package com.qa.recipes.controller;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.DeleteMapping;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.PutMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        import com.qa.recipes.domain.recipes;
        import com.qa.recipes.repo.reciperepo;
//
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class recipecontroller {

    private Logger logger = LoggerFactory.getLogger(recipecontroller.class);

    @Autowired
    private reciperepo reciperepo;

    @GetMapping("/recipes")
    public ResponseEntity<Object> getAllRecipes(){
        try {
            Iterable<recipes> customers = reciperepo.findAll();
            return new ResponseEntity<Object>(customers, HttpStatus.OK);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/recipes/{id}")
    public ResponseEntity<Object> getRecipeById(@PathVariable("id") Long id) {
        try {
            recipes recipes = reciperepo.findById(id).get();
            if(recipes != null) {
                return new ResponseEntity<Object>(recipes, HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/recipes")
    public ResponseEntity<Object> createRecipe(@RequestBody recipes recipes) {
        try {
            recipes savedRecipe = reciperepo.save(recipes);
            return new ResponseEntity<Object>(savedRecipe, HttpStatus.OK);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/recipes/{id}")
    public ResponseEntity<Object> updateRecipe(@PathVariable("id") Long id, @RequestBody recipes recipes) {
        try {
            recipes.setId(id);
            recipes savedCustomer = reciperepo.save(recipes);
            return new ResponseEntity<Object>(savedCustomer, HttpStatus.OK);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<HttpStatus> deleteRecipe(@PathVariable("id") Long id) {
        try {
            reciperepo.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }

}