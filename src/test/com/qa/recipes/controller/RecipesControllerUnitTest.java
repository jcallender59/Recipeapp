package com.qa.recipes.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.qa.recipes.domain.Recipes;
import com.qa.recipes.service.recipeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;


@WebMvcTest

//@SpringBootTest
//@AutoConfigureMockMvc


public class RecipesControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private recipeService service;

    @Test
    public void createTest() throws Exception {
        Recipes entry = new Recipes("Curry", "Mike", "Spices");
        String entryAsJSON = this.mapper.writeValueAsString(entry);

        Mockito.when(this.service.create(entry)).thenReturn(entry);

        mvc.perform(post("/recipes/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(entryAsJSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(entryAsJSON));
    }

    @Test
    public void testReadAll() throws Exception {
        List<Recipes> mockOutput = new ArrayList<Recipes>();
        mockOutput.add(new Recipes(1L, "Curry", "Phil", "Chicken, spices, water"));
        mockOutput.add(new Recipes(2L, "Irish stew", "James", "Lamb, veg, herbs"));
        String mockOutputAsJson = this.mapper.writeValueAsString(mockOutput);

        Mockito.when(this.service.getAll()).thenReturn(mockOutput);

        mvc.perform(get("/recipes/readAll"))
                .andExpect(status().isOk())
                .andExpect(content().json(mockOutputAsJson));
    }
//	@Test
//	public void testReadById() throws Exception {
//		long testId = 4L;
//		Recipes mockOutput = new Recipes(4L, "Lemonade", false, 2000, "Sainsburys", 29);
//		String mockOutputAsJson = this.mapper.writeValueAsString(mockOutput);
//
//		Mockito.when(this.service.getById(testId)).thenReturn(mockOutput);
//
//		mvc.perform(get("/recipes/getbyid/4"))
//				.andExpect(status().isOk())
//				.andExpect(content().json(mockOutputAsJson));
//	}

    @Test
    public void testUpdate() throws Exception {
        long testId = 5L;
        Recipes testRecipes = new Recipes("Veg soup","Peter", "Veg, herbs, water");
                Recipes mockOutput = new Recipes(5L,"Tomato soup", "Joe", "Tomato, water,spices");
                String testRecipesAsJson = this.mapper.writeValueAsString(testRecipes);
        String mockOutputAsJson = this.mapper.writeValueAsString(mockOutput);

        Mockito.when(this.service.update(testId, testRecipes))
                .thenReturn(mockOutput);

        mvc.perform(put("/recipes/update/5")
                        .content(testRecipesAsJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().json(mockOutputAsJson));
    }

    @Test
    public void testDelete() throws Exception {
        long testIdValid = 6L;
        long testIdInvalid = 7L;

        Mockito.when(this.service.delete(testIdValid)).thenReturn(true);
        Mockito.when(this.service.delete(testIdInvalid)).thenReturn(false);

        mvc.perform(delete("/recipes/delete/6"))
                .andExpect(status().isNoContent());

        mvc.perform(delete("/recipes/delete/7"))
                .andExpect(status().isNotFound());
    }


}
