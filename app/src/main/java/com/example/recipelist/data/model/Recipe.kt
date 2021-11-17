package com.example.recipelist.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "recipe_database")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
//    @ColumnInfo(name = "ingredients") val ingredients: List<String>
) : Parcelable