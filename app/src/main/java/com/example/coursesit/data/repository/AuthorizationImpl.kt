package com.example.coursesit.data.repository

import com.example.coursesit.data.AuthorizationSignIn
import com.example.coursesit.data.model.DataAuthorizationField
import com.example.coursesit.domain.repository.AuthorizationRepository
import com.example.coursesit.model.AuthorizationField

class AuthorizationImpl(val typeSignIn: AuthorizationSignIn): AuthorizationRepository {

    override fun signIn(data: AuthorizationField): Boolean {
        return typeSignIn.signIn(DataAuthorizationField(
            login = data.login,
            password = data.password)
        )
    }
}