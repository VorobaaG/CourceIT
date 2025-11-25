package com.example.data.repository

import com.example.data.AuthorizationSignIn
import com.example.data.dto.DataAuthorizationField
import com.example.domain.entity.AuthorizationField
import com.example.domain.repository.AuthorizationRepository


class AuthorizationRepositoryImpl(val typeSignIn: AuthorizationSignIn): AuthorizationRepository {

    override fun signIn(data: AuthorizationField): Boolean {
        return typeSignIn.signIn(
            DataAuthorizationField(
                login = data.login,
                password = data.password
            )
        )
    }
}