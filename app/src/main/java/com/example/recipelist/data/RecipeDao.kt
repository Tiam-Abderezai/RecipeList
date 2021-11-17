package com.example.recipelist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.recipelist.data.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(Recipe: Recipe)

    @Update
    suspend fun updateRecipe(Recipe: Recipe)

    @Delete
    suspend fun deleteRecipe (Recipe: Recipe)

    @Query("DELETE FROM recipe_database")
    suspend fun deleteAllRecipes()

    @Query("SELECT * FROM recipe_database ORDER BY id ASC")
    fun readAllData(): Flow<List<Recipe>>

}