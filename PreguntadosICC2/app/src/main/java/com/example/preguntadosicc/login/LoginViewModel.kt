package com.example.preguntadosicc.login

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.preguntadosicc.Login
import com.example.preguntadosicc.login.models.LoginInfo
import com.example.preguntadosicc.login.models.RegisterInfo
import com.example.preguntadosicc.login.models.User
import com.example.preguntadosicc.login.repository.UsersRepository
import com.example.preguntadosicc.navigation.LoginNavigator


class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var success = MutableLiveData<Boolean?>()
    lateinit var navigator : LoginNavigator
    var user = MutableLiveData<User?>()
    private val repository: UsersRepository

    init {
        repository = UsersRepository(application, getApplication())
    }

    fun setNavigator(activity: Login?) {
        navigator = LoginNavigator(activity)
    }

    fun registerUser(userRegister: RegisterInfo) {
        repository.registerUser(userRegister)
    }

    fun loginUser(userLogIn: LoginInfo) {
        repository.loginUser(userLogIn)
        success.postValue(true)
        user.postValue(repository.userTemp)
    }

    fun getUsers(){
        repository.getUsers()
    }

    fun logOut(){
        //user.postValue(repository.logOut())
    }



}