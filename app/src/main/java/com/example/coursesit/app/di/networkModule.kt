package com.example.coursesit.app.di


import com.example.data.api.CoursesApi
import com.example.data.repository.FakeCoursesApi
import com.example.domain.entity.Course
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module{


    single<CoursesApi>(named("fakeMainApi")) { FakeCoursesApi() }
    single<CoursesApi>(named("fakeFavoriteApi")) {(initCourses:List<Course>)-> FakeCoursesApi(fakeInitCourses = initCourses) }

    single<CoursesApi> (named("real"))  {
        Retrofit.Builder()
            .baseUrl("https://course.co/api/v0")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoursesApi::class.java)
    }

}