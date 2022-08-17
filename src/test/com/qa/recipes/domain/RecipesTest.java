package com.qa.recipes.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class RecipesTest {

    @Test
    public void testEquals() {
        EqualsVerifier.forClass(Recipes.class).usingGetClass().verify();
    }

    @Test
    public void noIdConstructor() {
        Recipes recipes= new Recipes("Curry", "Joe", "Spices, meat");

        //checks that when we create an object using our constructor, nothing is created as a null field
        assertNotNull(recipes.getName());
        assertNotNull(recipes.getAuthor());
        assertNotNull(recipes.getIngredients());

        assertEquals(recipes.getName(), "Curry");
        assertEquals(recipes.getAuthor(), "Joe");
        assertEquals(recipes.getIngredients(),"Spices, meat" );
    }

    @Test
    public void settersTest() {
        Recipes recipes= new Recipes();

        recipes.setName("Leek soup");
        recipes.setAuthor("Mike");
        recipes.setIngredients("Leeks, water");

        assertNotNull(recipes.getName());
        assertNotNull(recipes.getAuthor());
        assertNotNull(recipes.getIngredients());

        assertEquals(recipes.getName(), "Leek soup");
        assertEquals(recipes.getAuthor(), "Mike");
        assertEquals(recipes.getIngredients(), "Leeks, water" );
    }

}

