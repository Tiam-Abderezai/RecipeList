package com.example.recipelist.data.repo

import androidx.lifecycle.LiveData
import com.example.recipelist.data.model.Recipe
import com.example.recipelist.data.RecipeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class RecipeRepository(private val RecipeDao: RecipeDao) {
    val readAllData: Flow<List<Recipe>> = RecipeDao.readAllData().flowOn(Dispatchers.IO)
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
