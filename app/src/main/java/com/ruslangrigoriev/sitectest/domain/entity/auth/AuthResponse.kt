package com.ruslangrigoriev.sitectest.domain.entity.auth


import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("Authentication")
    val authentication: Authentication
)