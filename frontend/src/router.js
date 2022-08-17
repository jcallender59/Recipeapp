import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            alias: '/recipes',
            name: 'recipes',
            component: () => import('./components/RecipeList')
        },
        {
            path: '/recipes/:id',
            name: 'edit-recipe',
            component: () => import('./components/EditRecipe')
        },
        {
            path: '/add',
            name: 'add-recipe',
            component: () => import('./components/AddRecipe')
        }
    ]
})