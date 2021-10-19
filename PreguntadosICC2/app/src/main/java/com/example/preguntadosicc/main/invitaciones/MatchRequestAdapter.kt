package com.example.preguntadosicc.main.invitaciones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.R

class MatchRequestAdapter:
        RecyclerView.Adapter<MatchRequestAdapter.MatchRequestsViewHolder>() {

            private var requests = mutableListOf<MatchRequest>()

            inner class MatchRequestsViewHolder(private val view: View): RecyclerView.ViewHolder(view){
                val email = itemView.findViewById(R.id.nombrePartidaTextView) as TextView
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchRequestsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.solicitudes_partidas_view_holder, parent, false)
        return  MatchRequestsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchRequestsViewHolder, position: Int) {
        val solicitud = requests[position]
        holder.email.text = solicitud.player1
    }

    override fun getItemCount(): Int {
        return requests.size
    }

    fun setRequests(matches : MutableList<MatchRequest>){
        this.requests = matches
        this.notifyDataSetChanged()
    }
        }