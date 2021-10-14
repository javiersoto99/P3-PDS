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
    var database: UsersDao = DatabaseRepository(application).getUsersDao()
    var success = MutableLiveData<Boolean?>()
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()


    fun registerUser(userRegister: RegisterInfo) {
        val service = getRetrofit().create(UsersRemoteRepository::class.java)
        val call = service.registerUser(userRegister)
        call.enqueue(object :
            Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(
                    context ,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.code() == 200) {
                    val body = response.body()
                    if (body != null) {
                        executor.execute {
                            database.insertUser(UsersEntityMapper().mapToCached(body, userRegister))
                        }
                        getUsers()
                    }
                    Toast.makeText(context, "Registrado correctamente", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(context, "oops, algo salio mal", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    fun loginUser(userLogIn: LoginInfo) {
        val service = getRetrofit().create(UsersRemoteRepository::class.java)
        val call = service.logInUser(userLogIn)
        call.enqueue(object :
            Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(
                    context,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                var userLogin: User?
                if (response.code() == 200) {
                    val body = response.body()
                    if (body != null) {
                        if (body.message == null) {
                            executor.execute {
                                userLogin = UsersEntityMapper().mapFromCached(
                                    database.getUser(
                                        body.user_id,
                                        body.token
                                    )
                                )
                                if (userLogin != null) {
                                    userTemp = userLogin
                                    user.postValue(userTemp)
                                }
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "credenciales invalidas",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    Toast.makeText(context, "Haz iniciado sesion", Toast.LENGTH_LONG).show()
                    success.postValue(true)

                } else {
                    val gson = Gson()
                    val errorResponse: UserResponse = gson.fromJson(
                        response.errorBody()?.string(),
                        UserResponse::class.java
                    )
                    Toast.makeText(
                        context,
                        errorResponse.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    fun getUsers(){
        executor.execute {
            users = database.getAllUsers().map {
                UsersEntityMapper().mapFromCached(it)
            }
            myUsers.postValue(users.toMutableList())
        }
    }

    fun logOut() : User? {
        userTemp = null
        return userTemp
    }

}