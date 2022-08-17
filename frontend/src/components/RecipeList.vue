<template>
    <div>
        <table class="table">
            <thead>
                <tr>
                <th scope="col">Recipe Name</th>
                <th scope="col">Author</th>
                <th scope="col">Ingredients</th>
                <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody v-for="(recipes, index) in recipes" :key="index">
                <tr>
                    <td>{{recipes.name}}</td>
                    <td>{{recipes.author}}</td>
                    <td>{{recipes.ingredients}}</td>
                    <td><a :href="'/recipes/' + recipes.id" class="btn btn-primary">Edit</a></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import RecipeDataService from '../services/RecipeDataService'

export default {
    name: 'recipes',
    data() {
        return {
            recipes: []
        }
    },
    methods: {
        retrieveRecipes() {
            RecipeDataService.getAll()
                .then(response => {
                    this.recipes = response.data
                })
                .catch(e => {
                    alert(e)
                })
        }
    },
    mounted() {
        this.retrieveRecipes()
    }
}
</script>
