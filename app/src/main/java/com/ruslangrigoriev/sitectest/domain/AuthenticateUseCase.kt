package com.ruslangrigoriev.sitectest.domain

import javax.inject.Inject

class AuthenticateUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(uid: String, pass: String) = repository.authenticate(uid, pass)

}