package com.example.preguntadosicc.login.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.preguntadosicc.R
import com.example.preguntadosicc.login.LoginViewModel
import com.example.preguntadosicc.login.models.RegisterInfo
import com.example.preguntadosicc.networking.UsersRemoteRepository
import com.example.preguntadosicc.networking.getRetrofit
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class SignUpFragment : Fragment() {
    private val mLoginviewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val back2login = view.findViewById<Button>(R.id.backLoginBtn)
        back2login.setOnClickListener{
            mLoginviewModel.navigator.navigateToLogin()
        }

        val signUpSubmit = view.findViewById<Button>(R.id.signupSubmitButton)
        signUpSubmit.setOnClickListener{
            val email =view.findViewById<EditText>(R.id.emailEditText).text.toString()
            val username =view.findViewById<EditText>(R.id.usernameEditText).text.toString()
            val password =view.findViewById<EditText>(R.id.passwordEditText).text.toString()
            val user = RegisterInfo(email, username, password)

            signUp(user)
        }



        return view
    }

    private fun signUp(user: RegisterInfo){
        val service = getRetrofit(okHttpClient = OkHttpClient()).create(UsersRemoteRepository::class.java)

        service.registerUser(user).enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    context,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {


                if (response.code() == 200) {
                    Toast.makeText(context, "El usuario fue creado exitosamente", Toast.LENGTH_SHORT).show()

                    mLoginviewModel.navigator.navigateToLogin()
                } else {
                    Toast.makeText(context, "The user already exist", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}