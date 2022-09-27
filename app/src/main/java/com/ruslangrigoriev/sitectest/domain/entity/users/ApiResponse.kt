package com.ruslangrigoriev.sitectest.domain.entity.users


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("Users")
    val users: Users
)