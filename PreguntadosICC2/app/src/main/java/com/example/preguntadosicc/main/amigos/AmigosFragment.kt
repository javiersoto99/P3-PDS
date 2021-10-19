package com.example.preguntadosicc.main.amigos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.MainActivity
import com.example.preguntadosicc.R
import com.example.preguntadosicc.login.models.UserResponse
import com.example.preguntadosicc.main.models.Friend
import com.example.preguntadosicc.main.models.FriendResponse
import com.example.preguntadosicc.main.models.FriendsInfo
import com.example.preguntadosicc.navigation.LoginNavigator
import com.example.preguntadosicc.navigation.Navigator
import com.example.preguntadosicc.networking.FriendsRemoteRepository
import com.example.preguntadosicc.networking.MatchesRemoteRepository
import com.example.preguntadosicc.networking.getRetrofit
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.preguntadosicc.main.amigos.AmigosAdapter


class AmigosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AmigosAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_amigos, container, false)

        adapter = AmigosAdapter()
        recyclerView = view.findViewById(R.id.amigos_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val email = FriendsInfo("email0@domain.com")

        //LOAD FRIENDS

        val service = getRetrofit(okHttpClient = OkHttpClient()).create(FriendsRemoteRepository::class.java)

        service.friendsList(email).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    context,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                val gson = Gson()


                if (response.code() == 200) {

                    val friends = gson.fromJson(response.body()?.string(), FriendResponse::class.java)

                    adapter.setAmigos(friends.friends)

                    Toast.makeText(context,"Friends Loaded", Toast.LENGTH_LONG).show()



                } else {
                    Toast.makeText(context, "Error loading friends", Toast.LENGTH_SHORT).show()
                }
            }
        })

        // LOAD FRIENDS END


        return view
    }

    private fun loadFriends(friends: FriendsInfo){
        val service = getRetrofit(okHttpClient = OkHttpClient()).create(FriendsRemoteRepository::class.java)

        service.friendsList(friends).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    context,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                val gson = Gson()


                if (response.code() == 200) {

                    val friends = gson.fromJson(response.body()?.string(), FriendResponse::class.java)

                    Toast.makeText(context,"Login succesful", Toast.LENGTH_LONG).show()


                } else {
                    Toast.makeText(context, "Error loading friends", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}