package com.example.recipelist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.recipelist.data.model.Recipe
import com.example.recipelist.data.repo.RecipeRepository
import com.example.recipelist.data.RecipeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Recipe>>
    private val repository: RecipeRepository

    init {
        val recipeDao = RecipeDatabase.getDatabase(application).recipeDao()
        repository = RecipeRepository(recipeDao)
        readAllData = repository.readAllData
    }

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRecipe(recipe)
        }
    }

    fun updateRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateRecipe(recipe)
        }
    }

    fun deleteRecipe(recipe: Recipe){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteRecipe(recipe)
        }
    }
    fun deleteAllRecipe(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllRecipes()
        }
    }
}