package com.example.preguntadosicc.offline

data class Question(
    val id: Int,
    val category: String,
    val question: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int


)
