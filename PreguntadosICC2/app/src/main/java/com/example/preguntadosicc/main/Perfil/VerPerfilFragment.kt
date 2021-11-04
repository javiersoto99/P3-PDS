package com.example.preguntadosicc.main.Perfil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.MainActivity
import com.example.preguntadosicc.OnClickListener
import com.example.preguntadosicc.R
import com.example.preguntadosicc.login.LoginViewModel
import com.example.preguntadosicc.main.amigos.AmigosAdapter
import com.example.preguntadosicc.main.invitaciones.FriendRequestsInfo
import com.example.preguntadosicc.main.invitaciones.Match
import com.example.preguntadosicc.main.invitaciones.MatchFinishedResponse
import com.example.preguntadosicc.navigation.Navigator
import com.example.preguntadosicc.networking.MatchesRemoteRepository
import com.example.preguntadosicc.networking.getRetrofit
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerPerfilFragment : Fragment(), OnClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HistorialAdapter

    private val logInViewModel: LoginViewModel by activityViewModels()

    lateinit var navigator : Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logInViewModel.getCurrentUser()
        setNavigator(activity as MainActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ver_perfil, container, false)

        adapter = HistorialAdapter()
        recyclerView = view.findViewById(R.id.historial_RV)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        // LOAD FINISHED MATCHES

        logInViewModel.currentUser.observe(viewLifecycleOwner, {
            val email = FriendRequestsInfo(it.email)

            val matchService = getRetrofit(okHttpClient = OkHttpClient()).create(MatchesRemoteRepository::class.java)

            matchService.finishedMatches(email).enqueue(object : Callback<ResponseBody> {
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
                    val gson = Gson()

                    if (response.code() == 200){
                        val matchFinishedRequest = gson.fromJson(response.body()?.string(), MatchFinishedResponse::class.java)
                        adapter.setRequests(matchFinishedRequest.partidas)
                    }
                }
            })
        })

        return view
    }

    override fun onClickItem(item: Any) {
        navigator.navigateToDetalles()

        if (item is Match){
            
        }
    }

    fun setNavigator(activity: MainActivity?) {
        navigator = Navigator(activity)
    }
}