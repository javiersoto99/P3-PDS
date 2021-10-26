package com.example.preguntadosicc.main.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.R
import com.example.preguntadosicc.main.models.Friend
import com.example.preguntadosicc.main.models.GetPlayersInfo
import com.example.preguntadosicc.main.models.GetPlayersResponse
import com.example.preguntadosicc.networking.MatchesRemoteRepository
import com.example.preguntadosicc.networking.getRetrofit
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InvitarAmigosAdapter: RecyclerView.Adapter<InvitarAmigosAdapter.invitarAmigosViewHolder>() {
    private var amigos = mutableListOf<Friend>()

    inner class invitarAmigosViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val name = itemView.findViewById(R.id.nombre_amigo_tv) as TextView
        val invitarBtn = itemView.findViewById(R.id.invitar_amigo_btn) as Button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvitarAmigosAdapter.invitarAmigosViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.amigos_partida_view_holder, parent, false)
        return invitarAmigosViewHolder(view)
    }

    override fun onBindViewHolder(holder: InvitarAmigosAdapter.invitarAmigosViewHolder, position: Int) {
        val amigo = amigos[position]
        holder.name.text = amigo.username
        holder.invitarBtn.setOnClickListener {
            val sharedPref = it.context.getSharedPreferences("user", Context.MODE_PRIVATE)
            val service = getRetrofit(okHttpClient = OkHttpClient()).create(MatchesRemoteRepository::class.java)
            val inviteFriendInfo = InviteFriendInfo(sharedPref?.getInt("matchID", -1),
                                                    sharedPref?.getString("email", ""), amigo.email)

            service.invitePlayer(inviteFriendInfo).enqueue(object : Callback<ResponseBody>{
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
                    if(response.code() == 200){
                        Toast.makeText(it.context, "Invitacion Enviada", Toast.LENGTH_LONG).show()
                        holder.invitarBtn.isEnabled = false
                        holder.invitarBtn.isClickable = false
                        holder.invitarBtn.setBackgroundColor(ContextCompat.getColor(it.context, R.color.gray))
                    }
                }
            })
        }
        val sharedPref = holder.invitarBtn.context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val servicePlayers = getRetrofit(okHttpClient = OkHttpClient()).create(MatchesRemoteRepository::class.java)
        val getPlayersInfo = GetPlayersInfo(sharedPref?.getInt("matchID", 0))

        servicePlayers.getPlayers(getPlayersInfo).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    holder.invitarBtn.context,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val gson = Gson()
                if(response.code() == 200){
                    val players = gson.fromJson(response.body()?.string(), GetPlayersResponse::class.java)
                    val playersFixed = mutableListOf<String>()
                    for (player in players.players){
                        playersFixed.add(player.participant)
                    }
                    if(amigo.email in playersFixed){
                        holder.invitarBtn.isEnabled = false
                        holder.invitarBtn.isClickable = false
                        holder.invitarBtn.setBackgroundColor(ContextCompat.getColor(holder.invitarBtn.context, R.color.gray))
                    }
                }
            }
        })

    }

    override fun getItemCount(): Int {
        return amigos.size
    }

    fun setAmigos(friends : MutableList<Friend>){
        this.amigos = friends
        this.notifyDataSetChanged()
    }

}

