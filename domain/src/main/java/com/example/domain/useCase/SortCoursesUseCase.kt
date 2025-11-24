package com.example.domain.useCase

import com.example.domain.entity.Course
import com.example.domain.repository.CoursesRepository

class SortCoursesUseCase(private val coursesRepository: CoursesRepository) {

    suspend fun sortByTime(): List<Course>{
        return coursesRepository.sortByTime()
    }

}