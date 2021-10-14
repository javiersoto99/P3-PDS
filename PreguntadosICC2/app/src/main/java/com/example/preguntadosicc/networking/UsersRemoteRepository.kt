package com.example.preguntadosicc.networking

import com.example.preguntadosicc.login.models.LoginInfo
import com.example.preguntadosicc.login.models.RegisterInfo
import com.example.preguntadosicc.login.models.UserResponse
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersRemoteRepository {
    @POST("auth/register")
    fun registerUser(
        @Body info: RegisterInfo
    ): retrofit2.Call<ResponseBody>

    @POST("auth/login")
    fun logInUser(
        @Body info: LoginInfo
    ): retrofit2.Call<ResponseBody>

}