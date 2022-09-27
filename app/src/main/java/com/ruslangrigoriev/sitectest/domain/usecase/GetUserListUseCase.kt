package com.ruslangrigoriev.sitectest.domain.usecase

import com.ruslangrigoriev.sitectest.domain.repository.Repository
import javax.inject.Inject

class GetUserListUseCase @Inject constructor (
    private val repository: Repository
) {

    suspend operator fun invoke() = repository.getUserList()

}