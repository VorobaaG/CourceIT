package com.example.domain.repository

import com.example.domain.entity.Course


interface CoursesRepository {

    suspend fun getAll() : List<Course>
    suspend fun getById(id:Int) : Course
    suspend fun sortByTime() : List<Course>
    fun save()

}