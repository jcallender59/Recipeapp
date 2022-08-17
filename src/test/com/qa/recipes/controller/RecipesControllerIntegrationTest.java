package com.qa.recipes.controller;

import com.qa.recipes.domain.Recipes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:recipe-schema.sql", "classpath:recipe-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class RecipesControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void createTest() throws Exception {
        Recipes entry = new Recipes("Curry", "Joe", "Chicken, spices, water");
        Recipes result = new Recipes(2L, "Curry", "Joe", "Chicken, spices, water");

        String entryAsJSON = this.mapper.writeValueAsString(entry);
        String resultAsJSON = this.mapper.writeValueAsString(result);

        mvc.perform(post("/recipes/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(entryAsJSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(resultAsJSON));
    }

    @Test
    public void ReadOneTest() throws Exception {

        Recipes entry = new Recipes(1L,"Carrot soup", "Mike", "Carrot, water, spices");
        String entryAsJSON = this.mapper.writeValueAsString(entry);
        mvc.perform(get("/recipes/readById/1")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())

                .andExpect(content().json(entryAsJSON));
    }

    @Test
    public void testReadAll() throws Exception {
        List<Recipes> expectedList = new ArrayList<Recipes>();
        expectedList.add(new Recipes(1L,"Leek soup", "Phil", "Leeks, water"));

        String expectedListAsJson = mapper.writeValueAsString(expectedList);

        mvc.perform(get("/recipes/readAll"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedListAsJson));
    }

    @Test
    public void testUpdate() throws Exception {
        Recipes testRecipes = new Recipes("Lentil soup", "Clare", "Lentils, water, herbs");
        Recipes expectedRecipes = new Recipes(1L,"Lentil soup", "Clare", "Lentils, water, herbs");

        String testRecipesAsJson = mapper.writeValueAsString(testRecipes);
        String expectedRecipesAsJson = mapper.writeValueAsString(expectedRecipes);

        mvc.perform(put("/recipes/update/1")
                        .content(testRecipesAsJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().json(expectedRecipesAsJson));
    }

    @Test
    public void testDelete() throws Exception {
        mvc.perform(delete("/recipes/delete/1"))
                .andExpect(status().isNoContent());
    }
}




