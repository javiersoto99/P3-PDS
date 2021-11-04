package com.example.preguntadosicc.main.Perfil

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.R
import com.example.preguntadosicc.main.partidas.AnterioresAdapter
import com.example.preguntadosicc.networking.MatchesRemoteRepository
import com.example.preguntadosicc.networking.getRetrofit
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetallesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DetallesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_detalles, container, false)

        adapter = DetallesAdapter()
        recyclerView = view.findViewById(R.id.detalles_RV)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        // LOAD DETALLES

        val sharedPref = context?.getSharedPreferences("user", Context.MODE_PRIVATE)

        val requestInfo = AnswerInfo(sharedPref?.getInt("matchID2", 0), sharedPref?.getString("email", ""))

        val matchService = getRetrofit(okHttpClient = OkHttpClient()).create(MatchesRemoteRepository::class.java)

        matchService.matchStatistics(requestInfo).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    context,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val gson = Gson()

                if(response.code() == 200){
                    val answerRequest = gson.fromJson(response.body()?.string(), AnswerResponse::class.java)
                    adapter.setRequests(answerRequest.preguntas)
                }
            }
        })

        return view
    }


}