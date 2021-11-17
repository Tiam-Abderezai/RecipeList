package com.example.recipelist.data.repo

import androidx.lifecycle.LiveData
import com.example.recipelist.data.model.Recipe
import com.example.recipelist.data.RecipeDao

class RecipeRepository(private val RecipeDao: RecipeDao) {
    val readAllData: LiveData<List<Recipe>> = RecipeDao.readAllData()
    suspend fun addRecipe(Recipe: Recipe) {
        RecipeDao.addRecipe(Recipe)
    }

    suspend fun updateRecipe(Recipe: Recipe) {
        RecipeDao.updateRecipe(Recipe)
    }

    suspend fun deleteRecipe(Recipe: Recipe) {
        RecipeDao.deleteRecipe(Recipe)
    }

    suspend fun deleteAllRecipes() {
        RecipeDao.deleteAllRecipes()
    }
}
