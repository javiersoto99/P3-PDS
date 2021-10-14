package com.example.preguntadosicc.login.repository

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.preguntadosicc.login.models.LoginInfo
import com.example.preguntadosicc.login.models.RegisterInfo
import com.example.preguntadosicc.login.models.User
import com.example.preguntadosicc.login.models.UserResponse
import com.example.preguntadosicc.networking.UsersRemoteRepository
import com.example.preguntadosicc.networking.getRetrofit
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UsersRepository(application: Application, context: Context) {

    private val context: Context? = context
    var userTemp : User? = null
    var user = MutableLiveData<User?>()
    var users = listOf<User?>()
    var myUsers = MutableLiveData<MutableList<User?>>()
    var success = MutableLiveData<Boolean?>()
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()


    fun registerUser(userRegister: RegisterInfo) {

    }

    fun loginUser(userLogIn: LoginInfo) {

    }

    fun getUsers(){

    }

    fun logOut(){}
}