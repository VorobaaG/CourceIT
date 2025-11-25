package com.example.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [FavoriteCourseEntity::class ],
    version = 1
)
abstract class CourseDB: RoomDatabase() {
    abstract val courseDao: CourseDao
}