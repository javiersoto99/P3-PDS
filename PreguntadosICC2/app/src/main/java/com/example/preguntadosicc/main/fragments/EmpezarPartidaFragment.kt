package com.example.preguntadosicc.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.R
import com.example.preguntadosicc.login.LoginViewModel
import com.example.preguntadosicc.main.invitaciones.FriendRequestInfo
import com.example.preguntadosicc.main.invitaciones.FriendRequestsInfo
import com.example.preguntadosicc.main.models.FriendResponse
import com.example.preguntadosicc.main.models.FriendsInfo
import com.example.preguntadosicc.networking.FriendsRemoteRepository
import com.example.preguntadosicc.networking.getRetrofit
import com.google.gson.Gson
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response


class EmpezarPartidaFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: InvitarAmigosAdapter

    private val logInViewModel: LoginViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logInViewModel.getCurrentUser()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_empezar_partida, container, false)
        adapter = InvitarAmigosAdapter()
        recyclerView = view.findViewById(R.id.amigosRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        logInViewModel.currentUser.observe(viewLifecycleOwner, {
            val email = FriendsInfo(it.email)
            val friendService = getRetrofit(okHttpClient = OkHttpClient()).create(FriendsRemoteRepository::class.java)

            friendService.friendsList(email).enqueue(object : retrofit2.Callback<ResponseBody> {
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

                    if(response.code() == 200){
                        val friends = gson.fromJson(response.body()?.string(), FriendResponse::class.java)
                        adapter.setAmigos(friends.friends)
                    }
                    else{
                        Toast.makeText(context, "Error loading friends", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        })



        return view
    }


}