package com.ruslangrigoriev.sitectest.domain.usecase

import com.ruslangrigoriev.sitectest.domain.repository.Repository
import javax.inject.Inject

class AuthenticateUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(uid: String, pass: String) = repository.authenticate(uid, pass)

}