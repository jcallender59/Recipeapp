package com.qa.recipes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.recipes.domain.Recipes;
import com.qa.recipes.repo.recipeRepo;

@SpringBootTest
public class RecipesServiceUnitTest {

    //Autowiring mocks into the service class
    @Autowired
    private recipeService service;

    //we are mocking the repository class as we dont want to directly depend on the repo class itself
    @MockBean
    private recipeRepo repo;

    @Test
    public void createTest() {
        Recipes input = new Recipes("Soup", "Jack", "Veg, water" );
        Recipes output = new Recipes(1L, "Soup", "Jack", "Veg, water");

        //Here we are testing the actual method (within the create method from RecipesService)
        Mockito.when(this.repo.save(input)).thenReturn(output);

        //Here we are checking the expected value (output) is the same as the actual value (method itself)
        assertEquals(output, this.service.create(input));

        //Verifies that repo is run 1 time, and that it saves the input
        Mockito.verify(this.repo, Mockito.times(1)).save(input);
    }

    public void testGetAll() {
        List<Recipes> mockOutput = new ArrayList<Recipes>();
        mockOutput.add(new Recipes("Bread", "Phil", "Flour, water, yeast"));

        Mockito.when(this.repo.findAll()).thenReturn(mockOutput);

        assertEquals(mockOutput, this.service.getAll());

        Mockito.verify(this.repo, Mockito.times(1)).findAll();
    }


    @Test
    public void testGetById() {
        long testInputValid = 3L;
        long testInputInvalid = 4L;
        Optional<Recipes> mockOutputValid =
                Optional.ofNullable(new Recipes(3L, "Bread", "Phil", "Flour, water, yeast"));
                        Optional<Recipes> mockOutputInvalid = Optional.ofNullable(null);

        Mockito.when(this.repo.findById(testInputValid)).
                thenReturn(mockOutputValid);
        Mockito.when(this.repo.findById(testInputInvalid)).
                thenReturn(mockOutputInvalid);

        assertEquals(mockOutputValid.get(), this.service.getById(testInputValid));
        assertEquals(null, this.service.getById(testInputInvalid));

        Mockito.verify(this.repo, Mockito.times(1)).findById(testInputValid);
        Mockito.verify(this.repo, Mockito.times(1)).findById(testInputInvalid);
    }

    @Test
    public void testUpdate() {
        long testInputIdValid = 4L;
        long testInputIdInvalid = 5L;
        Recipes testInputRecipes = new Recipes("Apple cake", "Paul", "Apples, flour, sugar, water");

        Optional<Recipes> mockOutputValid =
                Optional.ofNullable(new Recipes(4L, "Apple cake", "Jack", "Apples, flour, sugar, water"));
        Optional<Recipes> mockOutputInvalid = Optional.ofNullable(null);
        Recipes methodResult = new Recipes(4L,"Apple cake", "Paul", "Apples, flour, sugar, water");

        Mockito.when(this.repo.findById(testInputIdValid)).
                thenReturn(mockOutputValid);
        Mockito.when(this.repo.saveAndFlush(methodResult)).
                thenReturn(methodResult);
        Mockito.when(this.repo.findById(testInputIdInvalid)).
                thenReturn(mockOutputInvalid);

        assertEquals(methodResult,
                this.service.update(testInputIdValid, testInputRecipes));
        assertEquals(null,
                this.service.update(testInputIdInvalid, testInputRecipes));

        Mockito.verify(this.repo, Mockito.times(1)).findById(testInputIdValid);
        Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(methodResult);
        Mockito.verify(this.repo, Mockito.times(1)).findById(testInputIdInvalid);
    }

    @Test
    public void testDelete() {
        long testInput= 5L;

        Mockito.when(this.repo.existsById(testInput)).thenReturn(false);

        assertEquals(true, this.service.delete(testInput));

        Mockito.verify(this.repo, Mockito.times(1)).deleteById(testInput);
        Mockito.verify(this.repo, Mockito.times(1)).existsById(testInput);
    }
}

