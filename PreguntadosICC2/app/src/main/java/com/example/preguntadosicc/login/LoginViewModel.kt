package com.example.preguntadosicc.login

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.preguntadosicc.Login
import com.example.preguntadosicc.database.UserDatabaseRepositiry
import com.example.preguntadosicc.database.user.UserDao
import com.example.preguntadosicc.database.user.UserEntityMapper
import com.example.preguntadosicc.login.models.LoginInfo
import com.example.preguntadosicc.login.models.RegisterInfo
import com.example.preguntadosicc.login.models.User
import com.example.preguntadosicc.login.models.UserResponse
import com.example.preguntadosicc.login.repository.UsersRepository
import com.example.preguntadosicc.navigation.LoginNavigator
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var success = MutableLiveData<Boolean?>()
    lateinit var navigator : LoginNavigator

    var currentUser = MutableLiveData<UserResponse>()
    var user : List<UserResponse> = listOf()
    val database: UserDao

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()


    init {
        database = UserDatabaseRepositiry(application).getUserDao()
    }

    fun setNavigator(activity: Login?) {
        navigator = LoginNavigator(activity)
    }

    fun getCurrentUser(){
        executor.execute {
            user = database.getUser().map {
                UserEntityMapper().mapFromCached(it)
            }
            currentUser.postValue(user[0])
        }
    }

    fun setCurrentUser(userLogged: UserResponse){
        currentUser.postValue(userLogged)
        executor.execute {
            database.clearTable()
            database.insertUser(UserEntityMapper().mapToCached(userLogged))
        }

    }

    fun loginUser(userLogIn: UserResponse) {
        setCurrentUser(userLogIn)
    }


    fun logOut(){
        //user.postValue(repository.logOut())
        executor.execute {
            database.clearTable()
        }
    }



}