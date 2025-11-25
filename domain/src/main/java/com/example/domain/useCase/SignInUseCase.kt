package com.example.domain.useCase

import com.example.domain.entity.AuthorizationField
import com.example.domain.repository.AuthorizationRepository


class SignInUseCase(private val authRep: AuthorizationRepository) {

    fun execute(data: AuthorizationField): Boolean{
        return authRep.signIn(data)
    }
}