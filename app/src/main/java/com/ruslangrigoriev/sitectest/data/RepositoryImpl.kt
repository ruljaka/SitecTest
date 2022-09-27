package com.ruslangrigoriev.sitectest.data

import android.content.Context
import com.ruslangrigoriev.sitectest.domain.Repository
import com.ruslangrigoriev.sitectest.domain.entity.auth.Authentication
import com.ruslangrigoriev.sitectest.domain.entity.users.User
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val appContext: Context
) : Repository {

    override suspend fun getUserList(): List<User>? {
        return processResponse {
            service.getUserList(imei = appContext.getIMEI())
        }?.users?.listUsers
    }

    override suspend fun authenticate(uid: String, pass: String): Authentication? {
        return processResponse {
            service.authenticate(imei = appContext.getIMEI(), uid = uid, pass = pass)
        }?.authentication
    }

    private suspend fun <T> processResponse(
        networkCall: suspend () -> Response<T>
    ): T? {
        try {
            val response = networkCall.invoke()
            if (response.code() == 200) {
                return response.body()
            } else {
                throw Throwable("code: ${response.code()}")
            }
        } catch (e: IOException) {
            throw Throwable("Check internet connection")
        }  catch (e: Exception) {
            throw Throwable("Unknown error")
        }
    }
}