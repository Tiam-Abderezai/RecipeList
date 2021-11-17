package com.example.recipelist.data.repo

import android.app.Application
import com.example.recipelist.data.model.Recipe
import com.example.recipelist.data.RecipeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext


class RecipeRepository(private val application: Application) {
    private val recipeDao by lazy {
        RecipeDatabase.getDatabase(application).recipeDao()
    }

    fun readAllData() = recipeDao.readAllData().flowOn(Dispatchers.IO)

    suspend fun addRecipe(recipe: Recipe) {
        recipeDao.addRecipe(recipe)
    }

    suspend fun updateRecipe(recipe: Recipe) {
        recipeDao.updateRecipe(recipe)
    }

    suspend fun deleteRecipe(recipe: Recipe) {
        recipeDao.deleteRecipe(recipe)
    }

    suspend fun deleteAllRecipes() {
        recipeDao.deleteAllRecipes()
    }
}
