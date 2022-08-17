<template>
    <div>
        <div v-if="!submitted">
            <div class="mb-3">
                <label for="firstName" class="form-label">Recipe Name</label>
                <input type="text" class="form-control" id="RecipeName" required name="RecipeName" v-model="recipes.name">
            </div>
            <div class="mb-3">
                <label for="lastName" class="form-label">Author</label>
                <input type="text" class="form-control" id="Author" required name="Author" v-model="recipes.author">
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Ingredients</label>
                <input type="email" class="form-control" id="Ingredients" required name="Ingredients" v-model="recipes.ingredients">
            </div>
            <div class="mb-3">
                <button @click="saverecipes" class="btn btn-primary">Submit</button>
            </div>
        </div>
        <div v-else>
            <div class="alert alert-success" role="alert">
                Save recipes successfully!
            </div>
            <button @click="newrecipes" class="btn btn-primary">Add New recipes</button>
        </div>
    </div>
</template>

<script>
import RecipeDataService from '../services/RecipeDataService'

export default {
    name: 'add-recipes',
    data() {
        return {
            recipes: {
                id: null,
                name: "",
                author: "",
                ingredients: "",
            },
            submitted: false
        }
    },
    methods: {
        saverecipes() {
           let data = {
                name: this.recipes.name,
                author: this.recipes.author,
                ingredients: this.recipes.ingredients,
            }
            RecipeDataService.create(data)
                .then(response => {
                    this.recipes.id = response.data.id
                    this.submitted = true;
                })
                .catch( e => {
                    alert(e)
                })
        },
        newrecipes() {
            this.submitted = false
            this.recipes = {}
        }
    }
}
</script>
