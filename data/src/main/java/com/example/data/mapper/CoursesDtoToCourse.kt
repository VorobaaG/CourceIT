package com.example.data.mapper




import com.example.data.dto.CourseDTO
import com.example.data.dto.CoursesListDTO
import com.example.domain.entity.Course
import java.text.SimpleDateFormat
import java.util.Locale


fun CourseDTO.toCourse(imageUri: String? = null):Course{
    return Course(
        image = imageUri,
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


fun List<Course>.toCoursesListDTO(): CoursesListDTO{
    return CoursesListDTO(this.map { it.toCoursesDto() })
}
fun Course.toCoursesDto(): CourseDTO{
    return CourseDTO(
        id = this.id,
        title = this.title,
        text = this.text,
        price = this.price.toString(),
        rate = this.rate.toString(),
        startDate = this.startDate.toString(),
        hasLike = this.hasLike,
        publishDate = this.publishDate,
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


