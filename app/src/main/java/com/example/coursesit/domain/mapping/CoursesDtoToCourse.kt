package com.example.coursesit.domain.mapping

import com.example.coursesit.data.model.CourseDTO
import com.example.coursesit.model.Course

fun CourseDTO.toCourse(image:Int):Course{
    return Course(
        image = image,
        text = this.text,
        price = this.price.toInt(),
        title = this.title,
        rate = this.rate.toDouble(),
        hasLike = this.hasLike,
        startDate = this.startDate,
        publishDate = this.publishDate
    )
}
