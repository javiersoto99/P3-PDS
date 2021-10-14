package com.example.preguntadosicc.login.models

data class UserResponse(
    val user_id : String,
    val token : String,
    val message : String?
)