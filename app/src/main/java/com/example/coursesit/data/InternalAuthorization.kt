package com.example.coursesit.data

import com.example.coursesit.data.model.DataAuthorizationField

class InternalAuthorization : AuthorizationSignIn {
    override fun signIn(data: DataAuthorizationField): Boolean {
        return true
    }
}