package com.example.coursesit.app.viewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class AuthorizationViewModel (

) : ViewModel() {

    private var currentPassword by mutableStateOf("")
    private var currentLogin by mutableStateOf("")
    var statePassword by mutableStateOf(true)
        private  set
    var stateLogin by mutableStateOf(true)
        private set

    fun changeValuePassword(password:String){
        currentPassword = password
        checkValidPassword()
    }

    fun changeValueLogin(login: String){
        currentLogin  = login.filter { it !in 'А'..'Я' && it !in 'а'..'я' }
        checkValidLogin()
    }

    fun getPassword(): String = currentPassword
    fun getLogin(): String = currentLogin


    fun checkValidPassword(){
        statePassword = currentPassword.isNotEmpty()
    }

    fun checkValidLogin(){
        stateLogin =  currentLogin.matches(Regex("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"))
    }


}

