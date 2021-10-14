package com.example.preguntadosicc.login.models

data class User(
    val username : String,
    val name : String,
    val password : String,
    val signInBody: SignInBody,
)

data class SignInBody(val userId: String, val token: String)