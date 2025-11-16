package com.example.coursesit.data

import com.example.coursesit.data.model.DataAuthorizationField

interface AuthorizationSignIn {
    fun signIn(data: DataAuthorizationField) : Boolean
}