package com.example.coursesit.app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Course
import com.example.domain.useCase.GetCoursesUseCase
import com.example.domain.useCase.SaveAndDeleteUseCase
import com.example.domain.useCase.SortCoursesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainPageViewModel(
    private val getCourse: GetCoursesUseCase,
    private val sortCourse: SortCoursesUseCase,
    private val saveAndDeleteUseCase: SaveAndDeleteUseCase
): ViewModel() {

    private val _currentCourses = MutableStateFlow(listOf<Course>())
    val currentCourses= _currentCourses.asStateFlow()

    private var sortByDate = false

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _currentCourses.value = getCourse.getAll()
        }
    }

    fun sortByTime(){
        viewModelScope.launch(Dispatchers.IO) {
            if(sortByDate) _currentCourses.value = sortCourse.sortByName(currentCourses.value)
            else _currentCourses.value = sortCourse.sortByTime()
            sortByDate = sortByDate.not()
        }
    }

    fun addInFavoriteList(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            _currentCourses.value = _currentCourses.value.map { course ->
                if (course.id == id) {
                    if (course.hasLike) {
                        saveAndDeleteUseCase.delete(course)
                    } else saveAndDeleteUseCase.save(course)
                    course.copy(hasLike = course.hasLike.not())
                } else course
            }
        }
    }

}