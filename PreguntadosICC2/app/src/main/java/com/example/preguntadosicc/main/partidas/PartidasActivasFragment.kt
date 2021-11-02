package com.example.preguntadosicc.main.partidas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.MainActivity
import com.example.preguntadosicc.OnClickListener
import com.example.preguntadosicc.R
import com.example.preguntadosicc.login.LoginViewModel
import com.example.preguntadosicc.login.models.UserResponse
import com.example.preguntadosicc.main.models.general.IdInfo
import com.example.preguntadosicc.main.models.partidas.PartidasResponse
import com.example.preguntadosicc.main.models.partidas.PartidasViewModel
import com.example.preguntadosicc.main.models.preguntas.QuestionsViewModel
import com.example.preguntadosicc.navigation.Navigator
import com.example.preguntadosicc.networking.MatchesRemoteRepository
import com.example.preguntadosicc.networking.getRetrofit
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PartidasActivasFragment: Fragment(), OnClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var partidasAdapter: PartidasRecyclerViewAdapter

    private val logInViewModel: LoginViewModel by activityViewModels()
    private val questionsViewModel : QuestionsViewModel by activityViewModels()
    private val partidasViewModel: PartidasViewModel by activityViewModels()

    lateinit var navigator : Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigator(activity as MainActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_partidas_activas,container, false)
        logInViewModel.currentUser.observe(viewLifecycleOwner,{
            partidasViewModel.getPartidas(it.email)
        })

        partidasAdapter = PartidasRecyclerViewAdapter(this)
        recyclerView = view.findViewById(R.id.partidas_activas_recycler_view)
        recyclerView.adapter = partidasAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        partidasViewModel.myCurrentMatches.observe(viewLifecycleOwner,{
            partidasAdapter.setPartidasACtivas(it)
        })
        return view

    }

    override fun onClickItem(item: Any) {
        if (item is PartidasResponse){
            partidasViewModel.setCurrentMatch(item)

            //Toast.makeText(context, item.id.toString() + " " + item.creador, Toast.LENGTH_SHORT).show()
            val turnService = getRetrofit(okHttpClient = OkHttpClient()).create(
                MatchesRemoteRepository::class.java)
            logInViewModel.currentUser.observe(viewLifecycleOwner,{
                turnService.checkUserTurn(item.id, it.email).enqueue(object :
                    Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t:Throwable){
                        println(
                            t.message + "POST chequear turno"
                        )

                    }
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                        val gson = Gson()
                        if (response.code() == 200){
                            Toast.makeText(context, "Es turno de responder", Toast.LENGTH_SHORT).show()
                            val idPregunta = gson.fromJson(response.body()?.string(), IdInfo::class.java)
                            questionsViewModel.getQuestion(idPregunta.id)
                            navigator.navitageToPregunta()
                        }
                        if(response.code() == 401){
                            Toast.makeText(context, "Aun no es tu turno", Toast.LENGTH_SHORT).show()
                        }
                        if(response.code() == 500){
                            Toast.makeText(context, "Internal Server Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            })

        }
    }

    fun setNavigator(activity: MainActivity?) {
        navigator = Navigator(activity)
    }
}