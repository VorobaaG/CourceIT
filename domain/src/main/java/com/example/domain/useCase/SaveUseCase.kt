package com.example.domain.useCase

import com.example.domain.entity.Course
import com.example.domain.repository.CoursesRepository

class SaveAndDeleteUseCase(private val coursesRepository: CoursesRepository) {

    suspend fun save(course: Course){
        coursesRepository.save(course = course)
    }
    suspend fun delete(course: Course){
        coursesRepository.delete(course = course)
    }

    suspend fun isHaveLiked(course: Course): Boolean{
        val searchCourse = coursesRepository.getById(course.id)
        return searchCourse?.hasLike == true
    }

}