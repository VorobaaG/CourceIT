package com.example.coursesit.domain.useCase

import com.example.coursesit.R.drawable.*
import com.example.coursesit.domain.mapping.toCourse
import com.example.coursesit.domain.repository.CoursesRepository
import com.example.coursesit.model.Course

class GetCoursesUseCase(private val coursesRepository: CoursesRepository) {

    suspend fun getAll(): List<Course>{
        val listCourse = coursesRepository.getAll()
        return listCourse.courses?.map { it.toCourse(getImage(it.id))}?:listOf()
    }

    suspend fun getById(id:Int): Course{
        return coursesRepository.getById(id = id).toCourse(1)
    }
    private fun getImage(id:Int):Int{

        val listImage = listOf(course_1,course_2,course_3)
        return if(id == 100) course_1
        else if (id ==101) course_2
        else if (id == 102) course_3
        else listImage.random()
    }

}