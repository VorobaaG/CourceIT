package com.example.data.mapper




import com.example.data.dto.CourseDTO
import com.example.domain.entity.Course



fun CourseDTO.toCourse(imageUri: String? = null):Course{
    return Course(
        image = imageUri,
        text = this.text,
        price = this.price.toInt(),
        title = this.title,
        rate = this.rate.toDouble(),
        hasLike = this.hasLike,
        startDate = this.startDate,
        publishDate = this.publishDate,
        id = this.id
    )
}
