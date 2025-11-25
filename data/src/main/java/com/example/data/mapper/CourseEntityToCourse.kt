package com.example.data.mapper



import com.example.data.dao.FavoriteCourseEntity
import com.example.domain.entity.Course
import java.text.SimpleDateFormat
import java.util.Locale

fun FavoriteCourseEntity.toCourse():Course{
    return Course(
        image = this.image,
        text = this.text,
        price = this.price.toInt(),
        title = this.title,
        rate = this.rate.toDouble(),
        hasLike = this.hasLike,
        startDate = formatDate(this.startDate),
        publishDate = this.publishDate,
        id = this.id
    )
}


fun List<FavoriteCourseEntity>.toListCourse(): List<Course>{
    return this.map { it.toCourse() }
}

fun Course.toFavoriteCourseEntity(): FavoriteCourseEntity{
    return FavoriteCourseEntity(
        image = this.image?:"",
        text = this.text,
        price = this.price.toInt(),
        title = this.title,
        rate = this.rate,
        hasLike = this.hasLike,
        startDate = this.startDate,
        publishDate = this.publishDate,
        id = this.id
    )
}


private fun formatDate(dataString: String): String{
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(dataString)
        outputFormat.format(date ?: return dataString)
    }catch (e: Exception){
        dataString
    }
}

