package com.qa.recipes.domain;

        import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import java.util.Objects;

@Entity
public class recipes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;

    private String Author;

    private String Ingredients;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String Ingredients) {
        this.Ingredients = Ingredients;
    }



    @Override
    public String toString() {
        return "Recipes [id=" + id + ", Name=" + Name + ", Author=" + Author + ", Ingredients =" + Ingredients + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        recipes recipe = (recipes) o;
        return Name == recipe.Name && Author == recipe.Author && Ingredients == recipe.Ingredients && Name.equals(recipe.Name) && Author.equals(recipe.Author) && Ingredients.equals(recipe.Ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, Author, Ingredients);
    }


}