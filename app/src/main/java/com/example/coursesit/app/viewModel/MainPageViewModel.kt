package com.example.coursesit.app.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursesit.R.drawable.course_1
import com.example.coursesit.R.drawable.course_2
import com.example.coursesit.domain.useCase.GetCoursesUseCase
import com.example.coursesit.model.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainPageViewModel(
    private val getCourse: GetCoursesUseCase
): ViewModel() {

    private val _currentCourses = MutableStateFlow(listOf<Course>())
    val currentCourses= _currentCourses.asStateFlow()



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

    @RequiresApi(Build.VERSION_CODES.O)
    fun sortByTime(){
        viewModelScope.launch(Dispatchers.IO) {
            val listCourses = getCourse.getAll()
            _currentCourses.value = listCourses.sortedBy { LocalDate.parse(it.startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")) }
        }
    }

    fun addInFavoriteList(id:Int){
        for(i in currentCourses.value){
            if(i.id == id){
                i.hasLike.not()
            }
        }
    }






}