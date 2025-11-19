package com.example.coursesit.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.coursesit.data.api.CoursesApi
import com.example.coursesit.data.model.CourseDTO
import com.example.coursesit.data.model.CoursesListDTO
import com.example.coursesit.domain.repository.CoursesRepository
import java.time.LocalDate

class InternetCoursesRepositoryImpl(private val api: CoursesApi): CoursesRepository {

    override suspend fun getAll(): CoursesListDTO {
        return api.getAllCourses()
    }

    override suspend fun getById(id: Int): CourseDTO {
        return  api.getCourseById(id = id.toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun sortByTime(): CoursesListDTO {
        val listCourses = api.getAllCourses()
        val sortList = listCourses.courses?.sortedBy { LocalDate.parse(it.startDate) }
        return CoursesListDTO(sortList)
    }

    override fun save() {
        TODO("Not yet implemented")
    }
}