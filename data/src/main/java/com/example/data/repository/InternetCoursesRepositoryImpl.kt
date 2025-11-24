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

    override suspend fun getById(id: Int): Course {
        return  api.getCourseById(id = id.toString()).toCourse()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun sortByTime(): List<Course>  {
        val listCourses = api.getAllCourses()
        val sortList = listCourses.courses?.sortedBy { LocalDate.parse(it.startDate) }
        return sortList?.map { it.toCourse() }?:listOf()

    }

    override fun save() {
        TODO("Not yet implemented")
    }

    private fun searchImage(id:Int): String{

        if(id == 100){
            return "android.resource://${context.packageName}/${course_1}".toUri().toString()
        }else if(id == 101){
            return "android.resource://${context.packageName}/${course_2}".toUri().toString()
        }else if(id == 102){
            return "android.resource://${context.packageName}/${course_3}".toUri().toString()
        }else
            return "android.resource://${context.packageName}/${course_1}".toUri().toString()
    }
}