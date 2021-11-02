package com.example.preguntadosicc.main.models.partidas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.preguntadosicc.main.models.preguntas.QuestionResponse
import com.example.preguntadosicc.networking.MatchesRemoteRepository
import com.example.preguntadosicc.networking.getRetrofit
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PartidasViewModel(application: Application): AndroidViewModel(application) {

    val currentMatches = mutableListOf<PartidasResponse>()
    val myCurrentMatches = MutableLiveData<MutableList<PartidasResponse>>()

    val currentMatch = MutableLiveData<PartidasResponse>()

    init {

    }

    fun addCurrentMatches(partida: PartidasResponse){
        currentMatches.add(partida)
        myCurrentMatches.postValue(currentMatches)
    }



    fun getPartidas(email:String){
        currentMatches.clear()
        val partidasService = getRetrofit(okHttpClient = OkHttpClient()).create(MatchesRemoteRepository::class.java)

        partidasService.getActiveMatches(email).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t:Throwable){
                println(
                    t.message + "GET Partidas activas"
                )

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val gson = Gson()

                if (response.code() == 200){
                    val partidas = gson.fromJson(response.body()?.string(), PartidasList::class.java)

                    partidas.partidas.forEach{
                        addCurrentMatches(it)
                    }
                }
            }
        })
    }

    fun setCurrentMatch(partida : PartidasResponse){
        currentMatch.postValue(partida)
    }
}