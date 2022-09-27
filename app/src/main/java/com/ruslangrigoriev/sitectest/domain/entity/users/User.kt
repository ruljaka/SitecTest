package com.ruslangrigoriev.sitectest.domain.entity.users


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("Language")
    val language: String,
    @SerializedName("Uid")
    val uid: String,
    @SerializedName("User")
    val user: String
)