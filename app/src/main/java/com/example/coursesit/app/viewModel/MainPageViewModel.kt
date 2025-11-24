package com.example.coursesit.app.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Course
import com.example.domain.useCase.GetCoursesUseCase
import com.example.domain.useCase.SortCoursesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class MainPageViewModel(
    private val getCourse: GetCoursesUseCase,
    private val sortCourse: SortCoursesUseCase
): ViewModel() {

    private val _currentCourses = MutableStateFlow(listOf<Course>())
    val currentCourses= _currentCourses.asStateFlow()

    private var sortByDate = false

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _currentCourses.value = getCourse.getAll()
        }
    }

    fun showAllFavoriteCourses(){
        viewModelScope.launch(Dispatchers.IO) {
            _currentCourses.value = currentCourses.value.filter { it.hasLike == true }.map {getCourse.getById(it.id) }
        }
    }


    fun sortByTime(){
        viewModelScope.launch(Dispatchers.IO) {
            if(sortByDate) _currentCourses.value = getCourse.getAll()
            else _currentCourses.value = sortCourse.sortByTime()
            sortByDate = sortByDate.not()
        }
    }

    fun addInFavoriteList(id:Int){
        _currentCourses.value = _currentCourses.value.map { course ->
            if (course.id == id) course.copy(hasLike = course.hasLike.not()) else course
        }
    }

}