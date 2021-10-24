package com.example.preguntadosicc.main.amigos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.MainActivity
import com.example.preguntadosicc.R
import com.example.preguntadosicc.login.LoginViewModel
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
import com.example.preguntadosicc.main.invitaciones.FriendRequestInfo


class AmigosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AmigosAdapter

    private val logInViewModel: LoginViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logInViewModel.getCurrentUser()
    }


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

        logInViewModel.currentUser.observe(viewLifecycleOwner,{
            val service = getRetrofit(okHttpClient = OkHttpClient()).create(FriendsRemoteRepository::class.java)

            val email = FriendsInfo(it.email)

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
        })

        val addFriendButton = view.findViewById<Button>(R.id.BuscarBtn)

        addFriendButton.setOnClickListener {
            val email = view.findViewById<EditText>(R.id.BuscarEditText).text.toString()

            val sharedPref = activity?.getSharedPreferences("user", Context.MODE_PRIVATE)

            val friendRequestInfo = FriendRequestInfo(sharedPref?.getString("email", ""), email)

            val service = getRetrofit(okHttpClient = OkHttpClient()).create(FriendsRemoteRepository::class.java)

            service.createFriendRequest(friendRequestInfo).enqueue(object: Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(
                        it.context,
                        t.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if(response.code() == 200) {
                        Toast.makeText(it.context, "Solicitud Creada", Toast.LENGTH_LONG).show()
                    }
                    else if(response.code() == 201){
                        Toast.makeText(it.context, "Solicitud Aceptada", Toast.LENGTH_LONG).show()
                    }
                    else if(response.code() == 202){
                        Toast.makeText(it.context, "Ya son amigos", Toast.LENGTH_LONG).show()
                    }
                    else if(response.code() == 203){
                        Toast.makeText(it.context, "La Solicitud ya existe", Toast.LENGTH_LONG).show()
                    }
                    else if(response.code() == 205){
                        Toast.makeText(it.context, "Usuario ya existe", Toast.LENGTH_LONG).show()
                    }
                    else {
                        Toast.makeText(it.context, "Error Creando Solicitud", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }

        //LOAD FRIENDS

        // LOAD FRIENDS END

        return view
    }

    private fun addFriend(friendInfo: FriendRequestInfo){
        val service = getRetrofit(okHttpClient = OkHttpClient()).create(FriendsRemoteRepository::class.java)

        service.createFriendRequest(friendInfo).enqueue(object: Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    context,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.code() == 200) {
                    Toast.makeText(context, "Solicitud Aceptada", Toast.LENGTH_LONG).show()
                }
                else {
                    Toast.makeText(context, "Error Aceptando Solicitud", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}