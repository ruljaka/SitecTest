package com.ruslangrigoriev.sitectest.domain

import com.ruslangrigoriev.sitectest.domain.Repository
import javax.inject.Inject

class GetUserListUseCase @Inject constructor (
    private val repository: Repository
) {

    suspend operator fun invoke() = repository.getUserList()

}