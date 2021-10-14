package com.example.preguntadosicc.networking

import com.example.preguntadosicc.login.models.LoginInfo
import com.example.preguntadosicc.login.models.RegisterInfo
import com.example.preguntadosicc.login.models.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersRemoteRepository {
    @POST("signup")
    fun registerUser(
        @Body info: RegisterInfo
    ): retrofit2.Call<UserResponse>

    @POST("login")
    fun logInUser(
        @Body info: LoginInfo
    ): retrofit2.Call<UserResponse>

}