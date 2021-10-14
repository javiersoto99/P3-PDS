package com.example.preguntadosicc.login.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.preguntadosicc.Login
import com.example.preguntadosicc.MainActivity
import com.example.preguntadosicc.R
import com.example.preguntadosicc.login.LoginViewModel
import com.example.preguntadosicc.login.models.LoginInfo

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
            val intent = Intent(activity, MainActivity::class.java)
            this.startActivity(intent)

            val email = view.findViewById<EditText>(R.id.emailLogInEditText).text.toString()
            val password = view.findViewById<EditText>(R.id.passwordLogInEditText).text.toString()
            val user = LoginInfo(email , password)


            mLoginviewModel.loginUser(user)

        }

        signupB.setOnClickListener{
            mLoginviewModel.navigator.navigateToRegister()
        }


        return view
    }

}