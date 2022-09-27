package com.ruslangrigoriev.sitectest.domain.entity.auth


import com.google.gson.annotations.SerializedName

data class Authentication(
    @SerializedName("ContinueWork")
    val continueWork: Boolean,
    @SerializedName("CurrentDate")
    val currentDate: String,
    @SerializedName("PhotoHash")
    val photoHash: String,
    @SerializedName("Response")
    val response: Boolean
)