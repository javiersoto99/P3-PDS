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
import com.example.preguntadosicc.main.invitaciones.*
import com.example.preguntadosicc.main.models.FriendResponse
import com.example.preguntadosicc.networking.FriendsRemoteRepository
import com.example.preguntadosicc.networking.MatchesRemoteRepository
import com.example.preguntadosicc.networking.getRetrofit
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InvitacionesFragment : Fragment() {

    private lateinit var friendRecyclerView: RecyclerView
    private lateinit var friendAdapter: FriendRequestsAdapter
    private lateinit var matchRecyclerView: RecyclerView
    private lateinit var matchAdapter: MatchRequestAdapter

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
        val view = inflater.inflate(R.layout.fragment_invitaciones, container, false)

        friendAdapter = FriendRequestsAdapter()
        friendRecyclerView = view.findViewById(R.id.amistad_recycler_view)
        friendRecyclerView.adapter = friendAdapter
        friendRecyclerView.layoutManager = LinearLayoutManager(activity)

        // LOAD FRIENDREQUESTS

        logInViewModel.currentUser.observe(viewLifecycleOwner, {
            val friendRequestsEmail = FriendRequestsInfo(it.email)

            val friendService = getRetrofit(okHttpClient = OkHttpClient()).create(FriendsRemoteRepository::class.java)

            friendService.friendRequests(friendRequestsEmail).enqueue(object : Callback<ResponseBody> {
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

                        val friendRequests = gson.fromJson(response.body()?.string(), FriendRequestsResponse::class.java)

                        friendAdapter.setRequests(friendRequests.requests)

                        Toast.makeText(context,"Friend Requests Loaded", Toast.LENGTH_LONG).show()



                    } else {
                        Toast.makeText(context, "Error Loading Friends Requests", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        })

        // LOAD FRIENDREQUESTS END

        matchAdapter = MatchRequestAdapter()
        matchRecyclerView = view.findViewById(R.id.partidas_recycler_view)
        matchRecyclerView.adapter = matchAdapter
        matchRecyclerView.layoutManager = LinearLayoutManager(activity)

        // LOAD MATCHREQUESTS

        logInViewModel.currentUser.observe(viewLifecycleOwner, {
            val matchRequestsEmail = MatchRequestsInfo(it.email)

            val matchService = getRetrofit(okHttpClient = OkHttpClient()).create(MatchesRemoteRepository::class.java)

            matchService.matchInvitations(matchRequestsEmail).enqueue(object : Callback<ResponseBody> {
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

                        val matchRequests = gson.fromJson(response.body()?.string(), MatchRequestsResponse::class.java)

                        matchAdapter.setRequests(matchRequests.matches)

                        Toast.makeText(context,"Match Requests Loaded", Toast.LENGTH_LONG).show()



                    } else {
                        Toast.makeText(context, "Error Loading Match Requests", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        })



        return view
    }


}