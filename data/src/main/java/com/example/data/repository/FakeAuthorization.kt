package com.example.data.repository

import com.example.data.AuthorizationSignIn
import com.example.data.dto.DataAuthorizationField

class FakeAuthorization : AuthorizationSignIn {
    override fun signIn(data: DataAuthorizationField): Boolean {
        return true
    }
}