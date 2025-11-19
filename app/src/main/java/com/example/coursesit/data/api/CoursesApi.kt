package com.example.coursesit.data.api

import com.example.coursesit.data.model.CourseDTO
import com.example.coursesit.data.model.CoursesListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoursesApi  {

    @GET("courses")
    suspend fun getAllCourses():CoursesListDTO

    @GET("courses")
    suspend fun getListCourses(
        @Query("limit") limit:Int,
        @Query("offset") offset:Int
    ):CoursesListDTO

    @GET("courses/{id}")
    suspend fun getCourseById(@Path("id") id: String): CourseDTO

}