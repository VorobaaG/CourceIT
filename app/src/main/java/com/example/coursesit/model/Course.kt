package com.example.coursesit.model

data class Course(
    val id:Int=0,
    val title:String,
    val text:String,
    val price:Int,
    val rate: Double,
    val startDate:String,
    var hasLike: Boolean,
    val publishDate:String,
    val image:Int
)