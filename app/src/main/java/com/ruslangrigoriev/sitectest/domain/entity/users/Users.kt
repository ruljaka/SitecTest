package com.ruslangrigoriev.sitectest.domain.entity.users


import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("ListUsers")
    val listUsers: List<User>
)