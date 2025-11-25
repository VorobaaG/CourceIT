package com.example.data.repository

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.api.CoursesApi
import com.example.data.mapper.toCourse
import com.example.domain.entity.Course
import com.example.domain.repository.CoursesRepository
import java.time.LocalDate
import com.example.data.R.drawable.*

import androidx.core.net.toUri


class InternetCoursesRepositoryImpl(private val context:Context,
    private val api: CoursesApi): CoursesRepository {

    override suspend fun getAll(): List<Course> {
        return api.getAllCourses().courses?.map { it.toCourse(searchImage(it.id))}?:emptyList()

    }

    override suspend fun getById(id: Int): Course? {
        return  api.getCourseById(id = id.toString()).toCourse(searchImage(id))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun sortByTime(): List<Course>  {
        val listCourses = api.getAllCourses()
        val sortList = listCourses.courses?.sortedBy { LocalDate.parse(it.startDate) }
        return sortList?.map { it.toCourse(searchImage(it.id)) }?:listOf()

    }

    override suspend fun save(course: Course): Boolean {
        return true
    }

    override suspend fun delete(course: Course): Boolean {
        return true
    }

    private fun searchImage(id:Int): String{

        val coursesImage = listOf(course_1,course_2,course_3)

        return if(id == 100){
            "android.resource://${context.packageName}/${course_1}".toUri().toString()
        }else if(id == 101){
            "android.resource://${context.packageName}/${course_2}".toUri().toString()
        }else if(id == 102){
            "android.resource://${context.packageName}/${course_3}".toUri().toString()
        }else
            "android.resource://${context.packageName}/${coursesImage.random()}".toUri().toString()
    }
}