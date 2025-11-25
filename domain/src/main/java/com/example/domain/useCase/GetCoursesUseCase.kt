package com.example.domain.useCase


import com.example.domain.entity.Course
import com.example.domain.repository.CoursesRepository


class GetCoursesUseCase(private val coursesRepository: CoursesRepository) {

    suspend fun getAll(): List<Course>{
        val listCourse = coursesRepository.getAll()
        return listCourse
    }

    suspend fun getById(id:Int): Course?{
     return coursesRepository.getById(id)
    }

}