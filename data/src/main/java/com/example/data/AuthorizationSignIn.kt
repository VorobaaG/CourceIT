package com.example.data

import com.example.data.dto.DataAuthorizationField

interface AuthorizationSignIn {
    fun signIn(data: DataAuthorizationField) : Boolean
}