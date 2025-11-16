package com.example.coursesit.domain.repository

import com.example.coursesit.model.AuthorizationField

interface AuthorizationRepository {

    fun signIn(data: AuthorizationField): Boolean

}