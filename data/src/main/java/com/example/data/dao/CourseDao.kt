package com.example.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(course: FavoriteCourseEntity)

    @Query("SELECT * FROM favorite_Course")
    fun getAll():List<FavoriteCourseEntity>?

    @Query("SELECT * FROM favorite_Course WHERE favorite_Course.id =:id")
    fun getById(id:Int): FavoriteCourseEntity?

    @Query("SELECT * FROM favorite_Course ORDER BY startDate")
    fun sortByTime():List<FavoriteCourseEntity>

    @Query("SELECT * FROM favorite_Course ORDER BY id")
    fun sortById():List<FavoriteCourseEntity>

    @Delete
    fun delete(course: FavoriteCourseEntity)
}