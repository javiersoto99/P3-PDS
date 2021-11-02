package com.example.preguntadosicc.main.models.preguntas

data class AnswerInfo(
    val answer_id: Int,
    val question_id: Int,
    val match_id: Int,
    val  user_email: String
)
