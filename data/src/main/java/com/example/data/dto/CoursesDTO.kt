package com.example.data.dto

data class  CoursesListDTO(
    val courses:List<CourseDTO>? =null
)

data class CourseDTO(
    val id:Int,
    val title:String,
    val text:String,
    val price: String,
    val rate: String,
    val startDate:String,
    val hasLike: Boolean,
    val publishDate:String
)
