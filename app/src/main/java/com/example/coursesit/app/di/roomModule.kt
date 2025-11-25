package com.example.coursesit.app.di

import androidx.room.Room
import com.example.data.dao.CourseDB
import org.koin.dsl.module

val roomModule = module{

    single {
        Room.databaseBuilder(
            get(),
            CourseDB::class.java,
            "course_database"
        )
            .build()
    }

    single { get<CourseDB>().courseDao }

}