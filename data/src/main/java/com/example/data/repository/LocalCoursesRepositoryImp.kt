package com.example.data.repository

import com.example.data.dao.CourseDao
import com.example.data.mapper.toCourse
import com.example.data.mapper.toFavoriteCourseEntity
import com.example.data.mapper.toListCourse
import com.example.domain.entity.Course
import com.example.domain.repository.CoursesRepository

class LocalCoursesRepositoryImp(
    private val course: CourseDao
): CoursesRepository {

    override suspend fun getAll(): List<Course> {
        return course.getAll()?.toListCourse()?:emptyList()
    }

    override suspend fun getById(id: Int): Course? {
        return course.getById(id)?.toCourse()
    }

    override suspend fun sortByTime(): List<Course> {
        return course.sortByTime().toListCourse()
    }

    fun sortById():List<Course>{
        return course.sortById().toListCourse()
    }

    override suspend fun save(course: Course): Boolean {
        try {
            this.course.insert(course = course.toFavoriteCourseEntity())
        }catch (e: Exception){
            return false
        }
            return true
    }

    override suspend fun delete(course:Course): Boolean{
        try {
            this.course.delete(course = course.toFavoriteCourseEntity())
        }catch (e: Exception){
            return false
        }
        return true
    }
}