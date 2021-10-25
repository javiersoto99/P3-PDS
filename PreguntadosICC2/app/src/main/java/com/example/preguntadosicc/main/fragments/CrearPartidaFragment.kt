package com.example.preguntadosicc.main.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.example.preguntadosicc.MainActivity
import com.example.preguntadosicc.R
import com.example.preguntadosicc.main.invitaciones.CategoriesResponse
import com.example.preguntadosicc.main.invitaciones.CreateMatchInfo
import com.example.preguntadosicc.navigation.Navigator
import com.example.preguntadosicc.networking.MatchesRemoteRepository
import com.example.preguntadosicc.networking.getRetrofit
import com.google.gson.Gson
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response


class CrearPartidaFragment : Fragment() {

    lateinit var navigator : Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigator(activity as MainActivity)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val matchService = getRetrofit(okHttpClient = OkHttpClient()).create(MatchesRemoteRepository::class.java)

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_crear_partida, container, false)

        val crearPartidaB = view.findViewById<Button>(R.id.crearBtn)
        val categoriesSpinner = view.findViewById<Spinner>(R.id.spinner_categoria1)

        matchService.getCategories().enqueue(object : retrofit2.Callback<ResponseBody> {
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
                    val categories = gson.fromJson(response.body()?.string(), CategoriesResponse::class.java)

                    val adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, categories.categories)
                    categoriesSpinner.adapter = adapter
                }
            }
        })

        crearPartidaB.setOnClickListener {
            val sharedPref = context?.getSharedPreferences("user", Context.MODE_PRIVATE)

            val createMatchInfo = CreateMatchInfo(sharedPref?.getString("email", ""), categoriesSpinner.selectedItem.toString())
            matchService.matchCreate(createMatchInfo).enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(
                        context,
                        t.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    navigator.navigateToEmpezarPartida()
                }
            })
        }

        return view
    }

    fun setNavigator(activity: MainActivity?) {
        navigator = Navigator(activity)
    }


}