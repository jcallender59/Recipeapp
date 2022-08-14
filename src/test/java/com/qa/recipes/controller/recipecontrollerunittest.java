package com.qa.recipes;

import java.util.List;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.recipes.domain.recipes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestController
@RequestMapping("/home")
public class recipecontrollerunittest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired

    public void createTest() throws Exception {
       recipes entry = new recipes("Curry", "Peter", "Chicken, spices, water");
        String entryAsJSON = this.mapper.writeValueAsString(entry);

        Mockito.when(this.service.create(entry)).thenReturn(entry);

        mvc.perform(post("/drink/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(entryAsJSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(entryAsJSON));
    }




    @GetMapping("/recipes")
    public ResponseEntity<List<Cat>> getCats() {
        List<Cat> catData = this.service.readAllCats();

        return new ResponseEntity<List<Cat>>(catData, HttpStatus.OK);
    }


    //Create
    @PostMapping("/createCat")
    public ResponseEntity<Cat> createCat(@RequestBody Cat cat) {
        Cat createCat = this.service.addCat(cat);

        return new ResponseEntity<Cat>(createCat, HttpStatus.CREATED);
    }

    // Put/Patch - Update
    // Put -- is a complete replacement
    // Patch -- some replacements

    @PutMapping("/updateCat/{id}")
    public ResponseEntity<Cat> updateCat(@RequestBody Cat cat, @PathVariable Long id) {
        Cat updateCat = service.updateCat(cat, id);

        return new ResponseEntity<Cat>(updateCat, HttpStatus.I_AM_A_TEAPOT);
    }

    @DeleteMapping("/deleteCat/{id}")
    public ResponseEntity<Boolean> deleteCat(@PathVariable Long id) {

        Boolean deletedCat = service.deleteByCatID(id);

        return (deletedCat) ? new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);


//		return new ResponseEntity<Boolean>(deletedCat, HttpStatus.NO_CONTENT);
    }

}
