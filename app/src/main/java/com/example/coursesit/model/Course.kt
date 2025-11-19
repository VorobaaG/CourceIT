package com.example.coursesit.model

data class Course(
    val title:String,
    val text:String,
    val price:Int,
    val rate: Double,
    val startDate:String,
    val hasLike: Boolean,
    val publishDate:String,
    val image:Int
)