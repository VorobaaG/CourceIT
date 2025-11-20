package com.example.coursesit.domain.useCase

import com.example.coursesit.domain.repository.AuthorizationRepository
import com.example.coursesit.model.AuthorizationField

class SignInUseCase(private val authRep: AuthorizationRepository) {

    fun execute(data: AuthorizationField): Boolean{
        return authRep.signIn(data)
    }
}