package com.qa.recipes.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@javax.persistence.Entity
public class Recipes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

    private String ingredients;

    //test constructor
    public Recipes(Long id, String name, String author, String ingredients) {
        super();
        this.id = id;
        this.name = name;
        this.author = author;
        this.ingredients = ingredients;
    }

    //production constructor
    public Recipes(String name, String author, String ingredients) {
        super();
        this.name = name;
        this.author= author;
        this.ingredients= ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    //Default constructor
    public Recipes() {

    }

    @Override
    public String toString() {
        return "Recipes [id=" + id + ", name=" + name + ", author=" + author + ", ingredients =" + ingredients   + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipes recipes = (Recipes) o;
        return name == recipes.name && author == recipes.author && ingredients == recipes.ingredients && ingredients.equals(recipes.ingredients) && author.equals(recipes.author) && name.equals(recipes.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, ingredients);
    }
}
