package com.example.preguntadosicc.networking

import com.example.preguntadosicc.main.models.general.IdInfo
import com.example.preguntadosicc.main.models.preguntas.AnswerInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface QuestionRemoteRepository {

    @GET("questions/question/{id}")
    fun getQuestion(
        @Path ("id")id: Int
    ): Call<ResponseBody>

    @GET("questions/answers/{id}")
    fun getAlternatives(
        @Path("id") id: Int
    ): Call<ResponseBody>

    @POST("questions/check")
    fun checkAnswer(
        @Body info : AnswerInfo
    ): Call<ResponseBody>
}