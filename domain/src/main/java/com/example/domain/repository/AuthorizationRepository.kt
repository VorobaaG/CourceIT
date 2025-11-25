package com.example.domain.repository

import com.example.domain.entity.AuthorizationField


interface AuthorizationRepository {

    fun signIn(data: AuthorizationField): Boolean

}