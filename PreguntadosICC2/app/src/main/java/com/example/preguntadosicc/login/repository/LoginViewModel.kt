package com.example.preguntadosicc.login.repository

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.preguntadosicc.Login
import com.example.preguntadosicc.navigation.LoginNavigator


class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var success = MutableLiveData<Boolean?>()
    lateinit var navigator : LoginNavigator


    fun setNavigator(activity: Login?) {
        navigator = LoginNavigator(activity)
    }



}