package com.example.recipelist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.recipelist.data.model.Recipe
@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecipe(Recipe: Recipe)

    @Update
    suspend fun updateRecipe(Recipe: Recipe)

    @Delete
    suspend fun deleteRecipe (Recipe: Recipe)

    @Query("DELETE FROM Recipe_table")
    suspend fun deleteAllRecipes()

    @Query("SELECT * FROM Recipe_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Recipe>>

}