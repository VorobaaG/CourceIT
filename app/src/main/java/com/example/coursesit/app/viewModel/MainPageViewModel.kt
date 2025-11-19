package com.example.coursesit.app.viewModel

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

class MainPageViewModel(
    private val getCourse: GetCoursesUseCase
): ViewModel() {

    private val _currentCourses = MutableStateFlow(listOf<Course>())
    val currentCourses= _currentCourses.asStateFlow()

    var favoriteList  = mutableSetOf<Int>()
        private set


    init {
        viewModelScope.launch(Dispatchers.IO) {
            _currentCourses.value = getCourse.getAll()
        }
    }

    fun showAllFavoriteCourses(){
        viewModelScope.launch(Dispatchers.IO) {
            _currentCourses.value = favoriteList.map {  getCourse.getById(it) }
        }
    }

    fun addInFavoriteList(id:Int){
        favoriteList.add(id)
    }






}