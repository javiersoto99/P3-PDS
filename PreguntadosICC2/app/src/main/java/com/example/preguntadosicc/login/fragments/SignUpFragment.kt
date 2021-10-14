package com.example.preguntadosicc.login.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.example.preguntadosicc.R
import com.example.preguntadosicc.login.LoginViewModel
import com.example.preguntadosicc.login.models.RegisterInfo

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

            mLoginviewModel.registerUser(user)
            mLoginviewModel.navigator.navigateToLogin()
        }



        return view
    }


}