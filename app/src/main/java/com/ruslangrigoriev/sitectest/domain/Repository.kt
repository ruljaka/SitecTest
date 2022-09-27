package com.ruslangrigoriev.sitectest.domain

import com.ruslangrigoriev.sitectest.domain.entity.auth.Authentication
import com.ruslangrigoriev.sitectest.domain.entity.users.User

interface Repository {
    suspend fun getUserList(): List<User>?
    suspend fun authenticate(uid:String, pass: String): Authentication?
}