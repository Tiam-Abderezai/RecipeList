package com.example.recipelist.viewmodel

import androidx.lifecycle.*
import com.example.recipelist.data.model.Recipe
import com.example.recipelist.data.repo.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class RecipeViewModel(
    private val recipeRepo: RecipeRepository
) : ViewModel()  {

        val recipes = recipeRepo.readAllData().asLiveData(viewModelScope.coroutineContext + Dispatchers.Default)

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeRepo.addRecipe(recipe)
        }
    }

    fun updateRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeRepo.updateRecipe(recipe)
        }
    }

    fun deleteRecipe(recipe: Recipe){
        viewModelScope.launch(Dispatchers.IO){
            recipeRepo.deleteRecipe(recipe)
        }
    }
    fun deleteAllRecipe(){
        viewModelScope.launch(Dispatchers.IO){
            recipeRepo.deleteAllRecipes()
        }
    }
    class Factory(
        private val contactRepo: RecipeRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
                return RecipeViewModel(contactRepo) as T
            } else {
                throw IllegalArgumentException("Cannot create instance of ContactViewModel")
            }
        }
    }
}