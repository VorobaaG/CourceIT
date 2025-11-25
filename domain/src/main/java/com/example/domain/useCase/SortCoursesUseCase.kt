package com.example.domain.useCase

import com.example.domain.entity.Course
import com.example.domain.repository.CoursesRepository
import java.time.LocalDate

class SortCoursesUseCase(private val coursesRepository: CoursesRepository) {

    suspend fun sortByTime(): List<Course>{
        return coursesRepository.sortByTime()
    }

    fun sortByTime(courses:List<Course>):List<Course>{
        return courses.sortedBy { LocalDate.parse(it.startDate) }
    }

    fun sortByName(courses: List<Course>): List<Course>{
        return courses.sortedBy { it.id }
    }

}