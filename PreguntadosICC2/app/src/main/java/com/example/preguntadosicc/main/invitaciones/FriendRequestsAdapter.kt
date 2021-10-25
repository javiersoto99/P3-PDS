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
import com.example.preguntadosicc.networking.FriendsRemoteRepository
import com.example.preguntadosicc.networking.getRetrofit
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class FriendRequestsAdapter:
    RecyclerView.Adapter<FriendRequestsAdapter.SolicitudesAmigosViewHolder>() {

        private var requests = mutableListOf<FriendRequest>()

        inner class SolicitudesAmigosViewHolder(private val view : View): RecyclerView.ViewHolder(view){
            val email = itemView.findViewById(R.id.nombre_amigo_tv) as TextView
            val acceptButton = itemView.findViewById(R.id.AceptarAmigoBtn) as Button
            val rejectButton = itemView.findViewById(R.id.RechazarAmigoBtn) as Button
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolicitudesAmigosViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.solicitudes_amigos_view_holder, parent, false)
            return SolicitudesAmigosViewHolder(view)
        }

        override fun onBindViewHolder(holder: SolicitudesAmigosViewHolder, position: Int) {
            val solicitud = requests[position]
            holder.email.text = solicitud.sender
            holder.acceptButton.setOnClickListener {
                val sharedPref = it.context.getSharedPreferences("user", Context.MODE_PRIVATE)

                val service = getRetrofit(okHttpClient = OkHttpClient()).create(FriendsRemoteRepository::class.java)
                val friendRequestInvitationInfo = FriendRequestInfo(sharedPref?.getString("email", ""), solicitud.sender)

                service.acceptFriendRequest(friendRequestInvitationInfo).enqueue(object : retrofit2.Callback<ResponseBody>{
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
                            Toast.makeText(it.context, "Solicitud Aceptada", Toast.LENGTH_LONG).show()
                            holder.acceptButton.isEnabled = false
                            holder.acceptButton.isClickable = false
                            holder.acceptButton.setBackgroundColor(ContextCompat.getColor(it.context, R.color.gray))
                            holder.rejectButton.isEnabled = false
                            holder.rejectButton.isClickable = false
                            holder.rejectButton.setBackgroundColor(ContextCompat.getColor(it.context, R.color.gray))
                        }
                        else {
                            Toast.makeText(it.context, "Error Aceptando Solicitud", Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }
            holder.rejectButton.setOnClickListener {
                val sharedPref = it.context.getSharedPreferences("user", Context.MODE_PRIVATE)

                val service = getRetrofit(okHttpClient = OkHttpClient()).create(FriendsRemoteRepository::class.java)
                val friendRequestInvitationInfo = FriendRequestInfo(sharedPref?.getString("email", ""), solicitud.sender)

                service.rejectFriendRequest(friendRequestInvitationInfo).enqueue(object : retrofit2.Callback<ResponseBody>{
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
                            Toast.makeText(it.context, "Solicitud Rechazada", Toast.LENGTH_LONG).show()
                            holder.acceptButton.isEnabled = false
                            holder.acceptButton.isClickable = false
                            holder.acceptButton.setBackgroundColor(ContextCompat.getColor(it.context, R.color.gray))
                            holder.rejectButton.isEnabled = false
                            holder.rejectButton.isClickable = false
                            holder.rejectButton.setBackgroundColor(ContextCompat.getColor(it.context, R.color.gray))
                        }
                        else {
                            Toast.makeText(it.context, "Error Rechazando Solicitud", Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }
        }

        override fun getItemCount(): Int {
            return requests.size
        }

        fun setRequests(solicitudes :MutableList<FriendRequest>){
            this.requests = solicitudes
            this.notifyDataSetChanged()
        }

    }