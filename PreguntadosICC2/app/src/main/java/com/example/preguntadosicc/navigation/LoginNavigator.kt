package com.example.preguntadosicc.navigation

import androidx.navigation.findNavController
import com.example.preguntadosicc.Login
import com.example.preguntadosicc.R

class LoginNavigator(val activity: Login?) {

    fun navigateToRegister(){
        activity?.findNavController(R.id.fragmentContainerLogin)?.navigate(R.id.action_logInFragment3_to_signUpFragment)

    }

    fun navigateToLogin(){
        activity?.findNavController(R.id.fragmentContainerLogin)?.navigate(R.id.action_signUpFragment_to_logInFragment3)

    }
}