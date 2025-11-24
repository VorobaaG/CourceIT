package com.example.data.api

import com.example.data.dto.CourseDTO
import com.example.data.dto.CoursesListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoursesApi  {

    @GET("courses")
    suspend fun getAllCourses(): CoursesListDTO

    @GET("courses")
    suspend fun getListCourses(
        @Query("limit") limit:Int,
        @Query("offset") offset:Int
    ): CoursesListDTO

    @GET("courses/{id}")
    suspend fun getCourseById(@Path("id") id: String): CourseDTO

}