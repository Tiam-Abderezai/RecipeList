package com.example.recipelist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdbkotlin.data.RecipeDatabase
import com.example.roomdbkotlin.repository.RecipeRepository
import com.example.roomdbkotlin.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Recipe>>
    private val repository: RecipeRepository

    init {
        val RecipeDao = RecipeDatabase.getDatabase(application).RecipeDao()
        repository = RecipeRepository(RecipeDao)
        readAllData = repository.readAllData
    }

    fun addRecipe(Recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRecipe(Recipe)
        }
    }

    fun updateRecipe(Recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateRecipe(Recipe)
        }
    }

    fun deleteRecipe(Recipe: Recipe){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteRecipe(Recipe)
        }
    }
    fun deleteAllRecipe(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllRecipes()
        }
    }
}