package com.example.data.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
    (tableName = "favorite_Course")
data class FavoriteCourseEntity(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val title:String,
    val text:String,
    val price: Int,
    val rate: Double,
    val startDate:String,
    val hasLike: Boolean,
    val publishDate:String,
    val image:String
)