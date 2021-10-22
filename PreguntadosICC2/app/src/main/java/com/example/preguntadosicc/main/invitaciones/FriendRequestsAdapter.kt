package com.example.preguntadosicc.main.invitaciones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.R

class FriendRequestsAdapter:
    RecyclerView.Adapter<FriendRequestsAdapter.SolicitudesAmigosViewHolder>() {

        private var requests = mutableListOf<FriendRequest>()

        inner class SolicitudesAmigosViewHolder(private val view : View): RecyclerView.ViewHolder(view){
            val email = itemView.findViewById(R.id.nombreAmigoUnidoTextView) as TextView
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolicitudesAmigosViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.solicitudes_amigos_view_holder, parent, false)
            return SolicitudesAmigosViewHolder(view)
        }

        override fun onBindViewHolder(holder: SolicitudesAmigosViewHolder, position: Int) {
            val solicitud = requests[position]
            holder.email.text = solicitud.sender
        }

        override fun getItemCount(): Int {
            return requests.size
        }

        fun setRequests(solicitudes :MutableList<FriendRequest>){
            this.requests = solicitudes
            this.notifyDataSetChanged()
        }

    }