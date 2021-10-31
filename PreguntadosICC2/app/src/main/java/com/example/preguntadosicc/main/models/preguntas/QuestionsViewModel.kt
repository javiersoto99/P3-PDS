package com.example.preguntadosicc.main.models.preguntas

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.preguntadosicc.networking.QuestionRemoteRepository
import com.example.preguntadosicc.networking.getRetrofit
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionsViewModel(application: Application): AndroidViewModel(application) {

    var currentAlternatives = mutableListOf<AlternativeResponse>()
    val myCurrentAlternatives = MutableLiveData<MutableList<AlternativeResponse>>()

    val currentQuestion = MutableLiveData<QuestionResponse>()

    init {
        //getQuestion(1)
        //getQuestionAlternatives(1)
    }

    fun addCurrentALternative(alternative: AlternativeResponse){
        currentAlternatives.add(alternative)
        myCurrentAlternatives.postValue(currentAlternatives)

    }

    fun addCurrentQuestion(question: QuestionResponse){
        currentQuestion.postValue(question)
    }

    fun getQuestion(id:Int){

        val questionService = getRetrofit(okHttpClient = OkHttpClient()).create(
            QuestionRemoteRepository::class.java)

        questionService.getQuestion(id).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t:Throwable){
                println(
                    t.message + "GET Pregunta"
                )

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val gson = Gson()

                if (response.code() == 200){
                    val question = gson.fromJson(response.body()?.string(), QuestionResponse::class.java)
                    println(question)
                    addCurrentQuestion(question)
                    getQuestionAlternatives(question.id)
                }
            }
        })

    }


    fun getQuestionAlternatives(id:Int){
        val questionsService = getRetrofit(okHttpClient = OkHttpClient()).create(
            QuestionRemoteRepository::class.java)

        questionsService.getAlternatives(id).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t:Throwable){
                println(
                    t.message + "GET ALTERNATIVAS"
                )

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val gson = Gson()

                if (response.code() == 200){
                    val alternativas = gson.fromJson(response.body()?.string(), QuestionOptionsList::class.java)
                    println("\n\n\n")
                    println("ACA")
                    println(alternativas)
                    println("\n\n\n")
                    alternativas.answers.forEach{
                      addCurrentALternative(it)

                    }
                }
            }
        })
    }

}