package com.example.coursesit.domain.repository

import com.example.coursesit.data.model.CourseDTO
import com.example.coursesit.data.model.CoursesListDTO

interface CoursesRepository {

    suspend fun getAll() : CoursesListDTO
    suspend fun getById(id:Int) : CourseDTO
    suspend fun sortByTime() : CoursesListDTO
    fun save()

}