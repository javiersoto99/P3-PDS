package com.example.preguntadosicc.login.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.preguntadosicc.Login
import com.example.preguntadosicc.MainActivity
import com.example.preguntadosicc.R
import com.example.preguntadosicc.login.LoginViewModel
import com.example.preguntadosicc.login.models.LoginInfo
import com.example.preguntadosicc.login.models.UserResponse
import com.example.preguntadosicc.networking.UsersRemoteRepository
import com.example.preguntadosicc.networking.getRetrofit
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInFragment : Fragment() {
    private val mLoginviewModel: LoginViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLoginviewModel.setNavigator(activity as Login)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mLoginviewModel.getUsers()

        val view = inflater.inflate(R.layout.fragment_log_in, container, false)
        val loginB = view.findViewById<Button>(R.id.loginBtn)
        val signupB = view.findViewById<Button>(R.id.signupBtn)

        mLoginviewModel.user.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra("Username", it.username)
                this.startActivity(intent)
            }
        })



        loginB.setOnClickListener{


            val email = view.findViewById<EditText>(R.id.emailLogInEditText).text.toString()
            val password = view.findViewById<EditText>(R.id.passwordLogInEditText).text.toString()
            val user = LoginInfo(email , password)

            //llamado a la api login
            signIn(user)




        }

        signupB.setOnClickListener{
            mLoginviewModel.navigator.navigateToRegister()
        }


        return view
    }

    private fun signIn(user: LoginInfo){
        val service = getRetrofit(okHttpClient = OkHttpClient()).create(UsersRemoteRepository::class.java)

        service.logInUser(user).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    context,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                val gson = Gson()


                if (response.code() == 200) {

                    val usuario = gson.fromJson(response.body()?.string(), UserResponse::class.java)

                    Toast.makeText(context,"Login succesful", Toast.LENGTH_LONG).show()
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)



                } else {
                    Toast.makeText(context, "User or password not valid", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }



}