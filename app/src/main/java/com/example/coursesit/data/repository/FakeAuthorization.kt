package com.example.coursesit.data.repository

import com.example.coursesit.data.AuthorizationSignIn
import com.example.coursesit.data.model.DataAuthorizationField

class FakeAuthorization : AuthorizationSignIn {
    override fun signIn(data: DataAuthorizationField): Boolean {
        return true
    }
}