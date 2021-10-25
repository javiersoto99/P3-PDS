package com.example.preguntadosicc.main.invitaciones

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
import com.example.preguntadosicc.login.LoginViewModel
import com.example.preguntadosicc.networking.FriendsRemoteRepository
import com.example.preguntadosicc.networking.MatchesRemoteRepository
import com.example.preguntadosicc.networking.getRetrofit
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchRequestAdapter:
        RecyclerView.Adapter<MatchRequestAdapter.MatchRequestsViewHolder>() {

    private var requests = mutableListOf<MatchRequest>()

            inner class MatchRequestsViewHolder(private val view: View): RecyclerView.ViewHolder(view){
                val email = itemView.findViewById(R.id.nombrePartidaTextView) as TextView
                val acceptButton = itemView.findViewById(R.id.AceptarPartidaBtn) as Button
                val rejectButton = itemView.findViewById(R.id.RechazarPartidaBtn) as Button
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchRequestsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.solicitudes_partidas_view_holder, parent, false)
        return  MatchRequestsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchRequestsViewHolder, position: Int) {
        val solicitud = requests[position]
        holder.email.text = solicitud.email
        holder.acceptButton.setOnClickListener {

            val sharedPref = it.context.getSharedPreferences("user", Context.MODE_PRIVATE)

            val service = getRetrofit(okHttpClient = OkHttpClient()).create(MatchesRemoteRepository::class.java)

            val matchRequestInvitationsInfo = MatchInvitationsInfo(sharedPref?.getString("email", ""), solicitud.id)

            service.matchAcceptInvitation(matchRequestInvitationsInfo).enqueue(object : Callback<ResponseBody>{
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
                    val gson = Gson()

                    if(response.code() == 200) {
                        Toast.makeText(it.context, "Partida Aceptada", Toast.LENGTH_LONG).show()
                        holder.acceptButton.isEnabled = false
                        holder.acceptButton.isClickable = false
                        holder.acceptButton.setBackgroundColor(ContextCompat.getColor(it.context, R.color.gray))
                        holder.rejectButton.isEnabled = false
                        holder.rejectButton.isClickable = false
                        holder.rejectButton.setBackgroundColor(ContextCompat.getColor(it.context, R.color.gray))
                    }
                    else {
                        Toast.makeText(it.context, "Error Aceptando Partida", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
        holder.rejectButton.setOnClickListener {
            val sharedPref = it.context.getSharedPreferences("user", Context.MODE_PRIVATE)

            val service = getRetrofit(okHttpClient = OkHttpClient()).create(MatchesRemoteRepository::class.java)

            val matchRequestInvitationsInfo = MatchInvitationsInfo(sharedPref?.getString("email", ""), solicitud.id)

            service.matchRejectInvitation(matchRequestInvitationsInfo).enqueue(object : Callback<ResponseBody>{
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
                    val gson = Gson()

                    if(response.code() == 200) {
                        Toast.makeText(it.context, "Partida Rechazada", Toast.LENGTH_LONG).show()
                        holder.acceptButton.isEnabled = false
                        holder.acceptButton.isClickable = false
                        holder.acceptButton.setBackgroundColor(ContextCompat.getColor(it.context, R.color.gray))
                        holder.rejectButton.isEnabled = false
                        holder.rejectButton.isClickable = false
                        holder.rejectButton.setBackgroundColor(ContextCompat.getColor(it.context, R.color.gray))
                    }
                    else {
                        Toast.makeText(it.context, "Error Rechazando Partida", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return requests.size
    }

    fun setRequests(matches : MutableList<MatchRequest>){
        this.requests = matches
        this.notifyDataSetChanged()
    }
        }