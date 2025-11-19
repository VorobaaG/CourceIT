package com.example.coursesit.app.di

import com.example.coursesit.data.api.CoursesApi
import com.example.coursesit.data.repository.FakeCoursesApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module{

    single<CoursesApi>(named("fake")) { FakeCoursesApi() }

    single<CoursesApi> (named("real"))  {
        Retrofit.Builder()
            .baseUrl("https://course.co/api/v0")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoursesApi::class.java)
    }

}