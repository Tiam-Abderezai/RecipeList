package com.example.recipelist.data

import androidx.room.*
import com.example.recipelist.data.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe_database")
    fun readAllData(): Flow<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(recipe: Recipe)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe (recipe: Recipe)

    @Query("DELETE FROM recipe_database")
    suspend fun deleteAllRecipes()

}