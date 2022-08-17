<template>
    <div v-if="currentRecipe">
        <div class="mb-3">
                <label for="firstName" class="form-label">Recipe Name</label>
                <input type="text" class="form-control" id="Name" required name="Name" v-model="currentRecipe.name">
            </div>
            <div class="mb-3">
                <label for="lastName" class="form-label">Author</label>
                <input type="text" class="form-control" id="Author" required name="Author" v-model="currentRecipe.author">
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Ingredients</label>
                <input type="email" class="form-control" id="Ingredients" required name="Ingredients" v-model="currentRecipe.ingredients">
            </div>
            <div class="mb-3">
                <button @click="updateRecipe" class="btn btn-primary me-3">Update</button>
                <button @click="deleteRecipe" class="btn btn-danger">Delete</button>
            </div>
             <div class="alert alert-success" role="alert" v-if="message">
                {{message}}
            </div>
    </div>
</template>

<script>
import RecipeDataService from '../services/RecipeDataService'

export default {
    name: 'edit-recipe',
    data() {
        return {
            currentRecipe: null,
            message: ''
        }
    },
    methods: {
        getRecipe(id) {
            RecipeDataService.get(id)
                .then(response => {
                    this.currentRecipe = response.data
                })
                .catch(e => {
                    alert(e)
                })
        },
        updateRecipe() {
            RecipeDataService.update(this.currentRecipe.id, this.currentRecipe)
                .then(() => {
                    this.message = 'The recipe was updated successfully!'
                })
                .catch(e => {
                    alert(e)
                })
        },
        deleteRecipe() {
            RecipeDataService.delete(this.currentRecipe.id)
                .then(() => {
                    this.$router.push({name: 'recipes'})
                })
                .catch(e => {
                    alert(e)
                })
        }
    },
    mounted() {
        this.getRecipe(this.$route.params.id)
    }
}
</script>
