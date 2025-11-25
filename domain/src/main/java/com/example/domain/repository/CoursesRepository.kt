package com.example.domain.repository

import com.example.domain.entity.Course


interface CoursesRepository {

    suspend fun getAll() : List<Course>
    suspend fun getById(id:Int) : Course?
    suspend fun sortByTime() : List<Course>
    suspend fun save(course: Course) : Boolean
    suspend fun delete(course: Course) : Boolean

}