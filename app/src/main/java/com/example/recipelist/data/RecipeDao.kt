package com.example.roomdbkotlin.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.recipelist.data.model.Recipe
@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecipe(recipe: Recipe)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe (recipe: Recipe)

    @Query("DELETE FROM Recipe_table")
    suspend fun deleteAllRecipes()

    @Query("SELECT * FROM Recipe_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Recipe>>

}