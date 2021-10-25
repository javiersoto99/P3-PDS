package com.example.preguntadosicc.main.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.R
import com.example.preguntadosicc.main.models.Friend

class invitarAmigosAdapter: RecyclerView.Adapter<invitarAmigosAdapter.invitarAmigosViewHolder>() {
    private var amigos = mutableListOf<Friend>()

    inner class invitarAmigosViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val name = itemView.findViewById(R.id.nombre_amigo_tv) as TextView
        val invitarBtn = itemView.findViewById(R.id.invitar_amigo_btn) as Button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): invitarAmigosAdapter.invitarAmigosViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.amigos_view_holder, parent, false)
        return invitarAmigosViewHolder(view)
    }

    override fun onBindViewHolder(holder: invitarAmigosAdapter.invitarAmigosViewHolder, position: Int) {
        val amigo = amigos[position]
        holder.name.text = amigo.username


    }

    override fun getItemCount(): Int {
        return amigos.size
    }


}

