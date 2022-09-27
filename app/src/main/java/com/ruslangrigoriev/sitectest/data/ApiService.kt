package com.ruslangrigoriev.sitectest.data

import com.ruslangrigoriev.sitectest.domain.entity.auth.AuthResponse
import com.ruslangrigoriev.sitectest.domain.entity.users.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("{IMEI}/form/users")
    suspend fun getUserList(
        @Path("IMEI") imei: String
    ): Response<ApiResponse>

    @GET("{IMEI}/authentication")
    suspend fun authenticate(
        @Path("IMEI") imei: String,
        @Query("uid") uid: String,
        @Query("pass") pass: String,
        @Query("copyFromDevice") copyFromDevice: Boolean = false,
        @Query("nfc") nfc: String = "",
    ): Response<AuthResponse>
}